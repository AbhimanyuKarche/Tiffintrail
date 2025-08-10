import React, { useEffect, useState } from "react";
import axiosConfig from "../utils/axiosConfig";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);

  const fetchCartItems = async () => {
    try {
      const res = await axiosConfig.get("/api/cart/items");
      setCartItems(res.data);
    } catch (err) {
      console.error("Error fetching cart:", err);
    }
  };

  useEffect(() => {
    fetchCartItems();
  }, []);

  const updateQuantity = async (itemId, quantity) => {
    try {
      await axiosConfig.put(
        `/api/cart/update?itemId=${itemId}&quantity=${quantity}`
      );
      fetchCartItems();
    } catch (err) {
      console.error("Error updating quantity:", err);
    }
  };

  const removeItem = async (itemId) => {
    try {
      await axiosConfig.delete(`/api/cart/remove?itemId=${itemId}`);
      fetchCartItems();
    } catch (err) {
      console.error("Error removing item:", err);
    }
  };

  const confirmOrder = async () => {
    try {
      await axiosConfig.post("/api/orders/confirm", { items: cartItems });
      alert("Order confirmed! 🎉");
      setCartItems([]);
    } catch (err) {
      console.error("Error confirming order:", err);
    }
  };

  return (
    <div className="p-4 max-w-3xl mx-auto">
      <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
      {cartItems.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <>
          {cartItems.map((item) => (
            <div
              key={item.id}
              className="flex justify-between items-center border p-4 mb-2 rounded shadow"
            >
              <div className="flex items-center gap-4">
                <img
                  src={item.imageUrl}
                  alt={item.tiffinName}
                  className="w-16 h-16 rounded"
                />
                <div>
                  <h4 className="font-semibold">{item.tiffinName}</h4>
                  <p>
                    ₹{item.pricePerUnit} × {item.quantity} = ₹{item.totalPrice}
                  </p>
                </div>
              </div>

              <div className="flex items-center gap-2">
                <button
                  onClick={() => updateQuantity(item.id, item.quantity - 1)}
                  className="px-2 py-1 bg-gray-200 rounded"
                  disabled={item.quantity <= 1}
                >
                  -
                </button>

                <span>{item.quantity}</span>

                <button
                  onClick={() => updateQuantity(item.id, item.quantity + 1)}
                  className="px-2 py-1 bg-gray-200 rounded"
                >
                  +
                </button>

                <button
                  onClick={() => removeItem(item.id)}
                  className="ml-4 px-3 py-1 bg-red-500 text-white rounded"
                >
                  Remove
                </button>
              </div>
            </div>
          ))}

          {/* Confirm Order Button */}
          <div className="mt-6 text-right">
            <button
              onClick={confirmOrder}
              disabled={cartItems.length === 0}
              className={`px-6 py-2 rounded font-bold text-white transition ${
                cartItems.length === 0
                  ? "bg-gray-400 cursor-not-allowed"
                  : "bg-green-500 hover:bg-green-600"
              }`}
            >
              Confirm Order
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default Cart;
