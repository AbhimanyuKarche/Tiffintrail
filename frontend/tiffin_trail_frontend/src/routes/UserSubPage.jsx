import React, { useState, useEffect } from "react";

export default function SubscriptionPage() {
  const [activeTab, setActiveTab] = useState("plans");
  const [plans, setPlans] = useState([]);
  const [subscriptions, setSubscriptions] = useState([]);
  const [loading, setLoading] = useState(false);

  const token = localStorage.getItem("token"); // Your auth token

  // Fetch Available Plans
  const fetchPlans = async () => {
    try {
      const res = await fetch(
        "http://localhost:8080/api/user-subscriptions/plans",
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      const data = await res.json();
      setPlans(data);
    } catch (err) {
      console.error("Error fetching plans:", err);
    }
  };

  // Fetch My Subscriptions
  const fetchSubscriptions = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/user-subscriptions", {
        headers: { Authorization: `Bearer ${token}` },
      });
      const data = await res.json();
      setSubscriptions(data);
    } catch (err) {
      console.error("Error fetching subscriptions:", err);
    }
  };

  // Subscribe to a plan
  const handleSubscribe = async (planId) => {
    setLoading(true);
    try {
      const res = await fetch(
        `http://localhost:8080/api/user-subscriptions/subscribe?planId=${planId}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify({ planId }),
        }
      );

      if (!res.ok) throw new Error("Subscription failed");

      await fetchSubscriptions(); // Refresh subscriptions list
      setActiveTab("subscriptions"); // Switch to My Subscriptions tab
    } catch (err) {
      console.error(err);
      alert("Failed to subscribe");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (activeTab === "plans") {
      fetchPlans();
    } else {
      fetchSubscriptions();
    }
  }, [activeTab]);

  return (
    <div className="max-w-4xl mx-auto mt-8 p-4 bg-white shadow-lg rounded-xl">
      {/* Tabs */}
      <div className="flex border-b border-gray-200 mb-4">
        <button
          onClick={() => setActiveTab("plans")}
          className={`flex-1 py-2 ${
            activeTab === "plans"
              ? "border-b-2 border-blue-500 font-semibold"
              : "text-gray-500"
          }`}
        >
          Available Plans
        </button>
        <button
          onClick={() => setActiveTab("subscriptions")}
          className={`flex-1 py-2 ${
            activeTab === "subscriptions"
              ? "border-b-2 border-blue-500 font-semibold"
              : "text-gray-500"
          }`}
        >
          My Subscriptions
        </button>
      </div>

      {/* Available Plans */}
      {activeTab === "plans" && (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {plans.length === 0 ? (
            <p>No plans available.</p>
          ) : (
            plans.map((plan) => (
              <div
                key={plan.id}
                className="border rounded-lg p-4 shadow hover:shadow-md transition"
              >
                <h3 className="text-lg font-bold">{plan.name}</h3>
                <p className="text-sm text-gray-600">{plan.description}</p>
                <p className="mt-2 font-semibold">₹{plan.price}</p>
                <p className="text-gray-500">
                  Duration: {plan.durationDays} days
                </p>
                {plan.tiffin && (
                  <p className="text-sm text-gray-700 mt-1">
                    Tiffin: {plan.tiffin.name}
                  </p>
                )}
                <button
                  onClick={() => handleSubscribe(plan.id)}
                  disabled={loading}
                  className="mt-3 w-full bg-blue-500 text-white py-1 rounded hover:bg-blue-600 disabled:opacity-50"
                >
                  {loading ? "Subscribing..." : "Subscribe"}
                </button>
              </div>
            ))
          )}
        </div>
      )}

      {/* My Subscriptions */}
      {activeTab === "subscriptions" && (
        <div className="space-y-4">
          {subscriptions.map((sub) => (
            <div
              key={sub.id}
              className="border rounded-lg p-4 shadow hover:shadow-md transition"
            >
              <h3 className="text-lg font-bold">{sub.plan.name}</h3>
              <p className="mt-2 font-semibold">₹{sub.plan.price}</p>
              <p className="text-gray-500">
                Start: {sub.startDate} | End: {sub.endDate}
              </p>
              <p className="text-gray-700">
                Status:{" "}
                <span
                  className={
                    sub.status === "ACTIVE" ? "text-green-600" : "text-red-600"
                  }
                >
                  {sub.status}
                </span>
              </p>
              <p className="text-gray-500">Paid: {sub.paid ? "Yes" : "No"}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
