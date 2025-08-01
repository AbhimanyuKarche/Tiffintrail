import React, { useState } from "react";
import axios from "axios";

const CustomerProfileForm = () => {
  const [formData, setFormData] = useState({
    userId: 1, // Hardcoded userId for now
    fullName: "",
    email: "",
    password: "",
    role: "",
    active: "",
  });

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/api/users", formData);
      console.log("Profile created:", res.data);
      alert("Profile saved successfully!");
    } catch (error) {
      console.error("Error saving profile", error);
      alert("Failed to save profile.");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>FullName:</label>
        <input
          type="text"
          name="fullName"
          value={formData.fullName}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>email:</label>
        <input
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Password:</label>
        <input
          type="text"
          name="password"
          value={formData.password}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Role:</label>
        <input
          type="text"
          name="role"
          value={formData.role}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Active:</label>
        <input
          type="boolean"
          name="isActive"
          value={formData.active}
          onChange={handleChange}
        />
      </div>
      <button type="submit">Submit Profile</button>
    </form>
  );
};

export default CustomerProfileForm;
