import React, { useEffect, useState } from "react";
import axios from "axios";

export default function ViewPlans() {
  const [plans, setPlans] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchPlans = async () => {
      try {
        const token = localStorage.getItem("token");
        const res = await axios.get("http://localhost:8080/api/seller/plans", {
          headers: { Authorization: `Bearer ${token}` },
        });
        setPlans(res.data);
      } catch (err) {
        console.error(err);
        setError("❌ Failed to load plans. Please try again.");
      } finally {
        setLoading(false);
      }
    };

    fetchPlans();
  }, []);

  if (loading)
    return (
      <div className="flex justify-center items-center h-64">
        <p className="text-gray-500 animate-pulse">Loading plans...</p>
      </div>
    );

  if (error)
    return (
      <div className="text-center text-red-600 font-medium mt-6">{error}</div>
    );

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-purple-50 to-pink-50 py-10 px-4">
      <div className="max-w-5xl mx-auto">
        <h2 className="text-3xl font-bold mb-6 text-gray-800">
          My Subscription Plans
        </h2>

        {plans.length === 0 ? (
          <div className="text-center py-10 bg-white/80 shadow rounded-lg backdrop-blur-sm">
            <p className="text-gray-500">You haven’t created any plans yet.</p>
          </div>
        ) : (
          <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
            {plans.map((plan) => (
              <div
                key={plan.id}
                className="bg-white/90 shadow-lg rounded-xl p-6 hover:shadow-2xl transition transform hover:-translate-y-1 border-l-4 border-blue-400"
              >
                <h3 className="text-xl font-semibold text-gray-800 mb-2">
                  {plan.name}
                </h3>
                <p className="text-gray-600 text-sm mb-4">
                  {plan.description || "No description"}
                </p>
                <div className="space-y-1 text-sm text-gray-700">
                  <p>
                    <span className="font-semibold">Price:</span> ₹
                    {Number(plan.price).toFixed(2)}
                  </p>
                  <p>
                    <span className="font-semibold">Duration:</span>{" "}
                    {plan.durationDays} days
                  </p>
                  <p>
                    <span className="font-semibold">Tiffin:</span>{" "}
                    {plan.tiffin?.name || "—"}
                  </p>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
