import React, { useEffect, useState } from "react";
import axios from "axios";

export default function CreatePlan() {
  const [tiffins, setTiffins] = useState([]);
  const [form, setForm] = useState({
    name: "",
    description: "",
    price: "",
    durationDays: "",
    tiffinId: "",
  });
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  // Fetch seller's tiffins
  useEffect(() => {
    const fetchTiffins = async () => {
      try {
        const token = localStorage.getItem("token");
        const res = await axios.get(
          "http://localhost:8080/api/tiffins/seller/my-tiffins",
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        setTiffins(res.data);
      } catch (error) {
        console.error(error);
        setMessage("Error fetching tiffins");
      }
    };
    fetchTiffins();
  }, []);

  // Handle input changes
  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  // Submit form
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");

    try {
      const token = localStorage.getItem("token");
      const payload = {
        name: form.name,
        description: form.description,
        price: parseFloat(form.price),
        durationDays: parseInt(form.durationDays, 10),
        tiffin: { id: parseInt(form.tiffinId, 10) },
      };

      await axios.post("http://localhost:8080/api/seller/plans", payload, {
        headers: { Authorization: `Bearer ${token}` },
      });

      setMessage("✅ Plan created successfully!");
      setForm({
        name: "",
        description: "",
        price: "",
        durationDays: "",
        tiffinId: "",
      });
    } catch (error) {
      console.error(error);
      setMessage("❌ Error creating plan");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-lg mx-auto mt-10 bg-white shadow-md rounded-lg p-6">
      <h2 className="text-2xl font-bold mb-4">Create Subscription Plan</h2>

      {message && (
        <p
          className={`mb-4 text-sm ${
            message.startsWith("✅") ? "text-green-600" : "text-red-600"
          }`}
        >
          {message}
        </p>
      )}

      <form onSubmit={handleSubmit} className="space-y-4">
        {/* Tiffin selection */}
        <select
          name="tiffinId"
          value={form.tiffinId}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded-lg focus:ring focus:ring-blue-300"
          required
        >
          <option value="">Select Tiffin</option>
          {tiffins.map((t) => (
            <option key={t.id} value={t.id}>
              {t.name} — ₹{t.price}
            </option>
          ))}
        </select>
        {/* Plan Name */}
        <input
          type="text"
          name="name"
          placeholder="Plan Name"
          value={form.name}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded-lg focus:ring focus:ring-blue-300"
          required
        />

        {/* Description */}
        <textarea
          name="description"
          placeholder="Description"
          value={form.description}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded-lg focus:ring focus:ring-blue-300"
          required
        />

        {/* Price */}
        <input
          type="number"
          name="price"
          step="0.01"
          placeholder="Price"
          value={form.price}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded-lg focus:ring focus:ring-blue-300"
          required
        />

        {/* Duration (manual input) */}
        <input
          type="number"
          name="durationDays"
          placeholder="Duration (days)"
          value={form.durationDays}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded-lg focus:ring focus:ring-blue-300"
          required
        />

        {/* Submit */}
        <button
          type="submit"
          disabled={loading}
          className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 disabled:opacity-50"
        >
          {loading ? "Creating..." : "Create Plan"}
        </button>
      </form>
    </div>
  );
}
