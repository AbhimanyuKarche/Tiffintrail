import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // <-- import useNavigate

const CheckoutPage = () => {
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate(); // <-- initialize navigate
  const token = localStorage.getItem("token");

  const loadRazorpayScript = () => {
    return new Promise((resolve) => {
      if (window.Razorpay) {
        resolve(true);
        return;
      }
      const script = document.createElement("script");
      script.src = "https://checkout.razorpay.com/v1/checkout.js";
      script.onload = () => resolve(true);
      script.onerror = () => resolve(false);
      document.body.appendChild(script);
    });
  };

  const handleCheckout = async () => {
    setLoading(true);
    try {
      // 1. Call backend to create order and get Razorpay order details
      const res = await fetch("http://localhost:8080/api/orders/place", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`, // <-- send token here
        },
      });
      const data = await res.json();

      if (!res.ok) {
        alert(data.error || "Failed to create order");
        setLoading(false);
        return;
      }

      // 2. Load Razorpay SDK script
      const loaded = await loadRazorpayScript();
      if (!loaded) {
        alert("Failed to load Razorpay SDK");
        setLoading(false);
        return;
      }

      // 3. Setup Razorpay options with backend response
      const options = {
        key: data.key, // Razorpay key id
        amount: data.amount, // in paise
        currency: data.currency,
        name: "TiffinTrail",
        description: "Order Payment",
        order_id: data.razorpayOrderId, // Razorpay order id
        handler: function (response) {
          alert(
            "Payment successful! Payment ID: " + response.razorpay_payment_id
          );
          setTimeout(() => navigate("/customerDashboard"), 1000); // <-- redirect after payment success
        },
        prefill: {
          email: "",
          contact: "",
        },
        theme: {
          color: "#2563eb",
        },
      };

      // 4. Open Razorpay Checkout
      const rzp = new window.Razorpay(options);
      rzp.open();
    } catch (error) {
      console.error(error);
      alert("Checkout failed");
    }
    setLoading(false);
  };

  return (
    <div className="p-6 max-w-md mx-auto text-center">
      <h2 className="text-3xl font-bold mb-6">Confirm Your Order</h2>
      <button
        onClick={handleCheckout}
        disabled={loading}
        className="px-6 py-3 bg-blue-600 hover:bg-blue-700 text-white rounded disabled:opacity-50"
      >
        {loading ? "Processing..." : "Place Order & Pay"}
      </button>
    </div>
  );
};

export default CheckoutPage;
