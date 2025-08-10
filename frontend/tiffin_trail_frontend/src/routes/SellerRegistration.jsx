import React, { useState } from "react";
import axios from "axios";
import boardFormImage from "../assets/CuttingBoard.png"; // <-- ✅ Import image like this

const TestingSeller = () => {
  const [formData, setFormData] = useState({
    businessName: "",
    description: "",
    address: "",
    city: "",
    pincode: "",
    phone: "",
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    if (!token) {
      setMessage("You must be logged in to create a profile.");
      return;
    }

    try {
      const res = await axios.post(
        "http://localhost:8080/api/seller/profile",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      alert("✅ Profile created successfully!");
    } catch (err) {
      console.error(err);
      if (err.response?.status === 403) {
        setMessage("❌ Access denied. Please login again.");
      } else {
        setMessage("❌ Something went wrong!");
      }
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center p-4">
      <div className="relative w-[1000px] flex justify-center items-center">
        {/* Background image */}
        <img
          src={boardFormImage}
          alt="Form Background"
          className="absolute top-0 left-0 w-full h-full object-contain pointer-events-none select-none z-0"
        />

        {/* Foreground form */}
        <form
          onSubmit={handleSubmit}
          className="relative z-10 w-[80%] max-w-[400px] flex flex-col gap-4 pt-10 pb-40"
        >
          <input
            type="text"
            name="businessName"
            placeholder="Business Name"
            value={formData.businessName}
            onChange={handleChange}
            className="p-3 rounded-full bg-white bg-opacity-90 focus:outline-none"
            required
          />
          <input
            type="text"
            name="description"
            placeholder="Description"
            value={formData.description}
            onChange={handleChange}
            className="p-3 rounded-full bg-white bg-opacity-90 focus:outline-none"
            required
          />
          <input
            type="text"
            name="address"
            placeholder="Address"
            value={formData.address}
            onChange={handleChange}
            className="p-3 rounded-full bg-white bg-opacity-90 focus:outline-none"
            required
          />
          <input
            type="text"
            name="city"
            placeholder="City"
            value={formData.city}
            onChange={handleChange}
            className="p-3 rounded-full bg-white bg-opacity-90 focus:outline-none"
            required
          />
          <input
            type="text"
            name="pincode"
            placeholder="Pincode"
            value={formData.pincode}
            onChange={handleChange}
            className="p-3 rounded-full bg-white bg-opacity-90 focus:outline-none"
            required
          />
          <input
            type="tel"
            name="phone"
            placeholder="Phone"
            value={formData.phone}
            onChange={handleChange}
            className="p-3 rounded-full bg-white bg-opacity-90 focus:outline-none"
            required
          />
          <button
            type="submit"
            className="self-center px-6 py-2 bg-green-600 text-white font-bold rounded-full hover:bg-green-700"
          >
            SUBMIT
          </button>
        </form>
      </div>
    </div>
  );
};

export default TestingSeller;
