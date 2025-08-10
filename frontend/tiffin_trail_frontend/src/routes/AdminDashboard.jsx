import React, { useEffect, useState } from "react";

export default function AdminDashboard() {
  const [pendingSellers, setPendingSellers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const API_BASE = "http://localhost:8080/admin"; // Change if needed
  const token = localStorage.getItem("token"); // JWT stored after login

  // Fetch pending sellers
  const fetchPendingSellers = async () => {
    try {
      setLoading(true);
      const res = await fetch(`${API_BASE}/pending-sellers`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (!res.ok) throw new Error("Failed to fetch sellers");
      const data = await res.json();
      console.log(data);
      setPendingSellers(data);
    } catch (err) {
      setError(err.message || "Something went wrong");
    } finally {
      setLoading(false);
    }
  };

  // Update seller status
  const updateSellerStatus = async (id, status) => {
    try {
      const res = await fetch(
        `${API_BASE}/seller/${id}/status?status=${status}`,
        {
          method: "POST",
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      if (!res.ok) throw new Error("Failed to update status");
      await fetchPendingSellers(); // refresh list
    } catch (err) {
      alert(err.message || "Error updating seller status");
    }
  };

  useEffect(() => {
    fetchPendingSellers();
  }, []);

  return (
    <div className="min-h-screen bg-gray-50 p-6">
      <div className="max-w-5xl mx-auto bg-white rounded-2xl shadow-lg p-6">
        <h1 className="text-2xl font-bold mb-4 text-gray-800">
          Admin Dashboard
        </h1>

        {loading && <p className="text-gray-500">Loading pending sellers...</p>}
        {error && <p className="text-red-500">{error}</p>}

        {!loading && !error && pendingSellers.length === 0 && (
          <p className="text-gray-600">No pending seller requests.</p>
        )}

        {!loading && pendingSellers.length > 0 && (
          <div className="overflow-x-auto">
            <table className="w-full text-sm text-left border border-gray-200 rounded-lg">
              <thead className="bg-gray-100">
                <tr>
                  <th className="px-4 py-2">ID</th>
                  <th className="px-4 py-2">Name</th>
                  <th className="px-4 py-2">Email</th>
                  <th className="px-4 py-2">Status</th>
                  <th className="px-4 py-2">Actions</th>
                </tr>
              </thead>
              <tbody>
                {pendingSellers.map((seller) => (
                  <tr key={seller.id} className="border-t">
                    <td className="px-4 py-2">{seller.id}</td>
                    <td className="px-4 py-2">
                      {seller.user?.fullName || "N/A"}
                    </td>
                    <td className="px-4 py-2">{seller.user?.email || "N/A"}</td>
                    <td className="px-4 py-2">{seller.approvalStatus}</td>
                    <td className="px-4 py-2 flex gap-2">
                      <button
                        onClick={() =>
                          updateSellerStatus(seller.id, "APPROVED")
                        }
                        className="px-3 py-1 bg-green-500 hover:bg-green-600 text-white rounded-md text-xs font-medium"
                      >
                        Approve
                      </button>
                      <button
                        onClick={() =>
                          updateSellerStatus(seller.id, "REJECTED")
                        }
                        className="px-3 py-1 bg-red-500 hover:bg-red-600 text-white rounded-md text-xs font-medium"
                      >
                        Reject
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}
