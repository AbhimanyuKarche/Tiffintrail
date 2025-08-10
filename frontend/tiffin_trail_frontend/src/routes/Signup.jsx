import React, { useState } from "react";
import axios from "axios";
import "../CSS/Forms.css"; // your animated layout styles
import { useNavigate } from "react-router-dom";
const CustomerProfileForm = () => {
  const navigate = useNavigate();

  const [isActive, setIsActive] = useState(false);

  // Switch between Sign In / Sign Up UI
  const handleRegisterClick = () => setIsActive(true);
  const handleLoginClick = () => setIsActive(false);

  // Sign Up state
  const [signUpData, setSignUpData] = useState({
    fullName: "",
    email: "",
    password: "",
    role: "",
  });

  // Sign In state
  const [signInData, setSignInData] = useState({
    email: "",
    password: "",
  });

  // Common input change handler
  const handleChange = (e, setState) => {
    const { name, value } = e.target;
    setState((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Submit: Registration
  const handleRegisterSubmit = async (e) => {
    e.preventDefault();
    console.log(signUpData);
    try {
      const res = await axios.post(
        "http://localhost:8080/api/auth/register",
        signUpData
      );
      console.log("User registered:", res.data);
      alert("Registration successful!");
    } catch (error) {
      console.error("Registration failed", error);
      alert("Failed to register.");
    }
  };

  // Submit: Login
  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post(
        "http://localhost:8080/api/auth/login",
        signInData
      );
      console.log(res.data);
      const { token, role, fullName } = res.data;

      localStorage.setItem("token", token);
      localStorage.setItem("role", role);
      localStorage.setItem("fullName", fullName);

      alert("Login successful!");

      if (role === "SELLER") {
        navigate("/sellermpage");
      } else if (role === "ADMIN") {
        navigate("/adminDashboard");
      } else if (role === "CUSTOMER") {
        // 🔍 Check if customer profile exists
        const profileCheck = await axios.get(
          "http://localhost:8080/api/customer/profile/exists",
          { headers: { Authorization: `Bearer ${token}` } }
        );

        if (profileCheck.data === true) {
          navigate("/customerDashboard");
        } else {
          navigate("/customerRegistration");
        }
      }
    } catch (error) {
      console.error("Login failed", error);
      alert("Invalid credentials");
    }
  };

  return (
    <div className="Baap">
      <div className={`container ${isActive ? "active" : ""}`} id="container">
        {/* 🔵 Sign Up */}
        <div className="form-container sign-up">
          <form onSubmit={handleRegisterSubmit}>
            <h1>Create Account</h1>
            <span>or use your email for registration</span>

            <input
              type="text"
              name="fullName"
              placeholder="Full Name"
              value={signUpData.fullName}
              onChange={(e) => handleChange(e, setSignUpData)}
              required
            />
            <input
              type="email"
              name="email"
              placeholder="Email"
              value={signUpData.email}
              onChange={(e) => handleChange(e, setSignUpData)}
              required
            />
            <input
              type="password"
              name="password"
              placeholder="Password"
              value={signUpData.password}
              onChange={(e) => handleChange(e, setSignUpData)}
              required
            />
            <select
              name="role"
              value={signUpData.role}
              onChange={(e) => handleChange(e, setSignUpData)}
              required
            >
              <option value="">Select Role</option>
              <option value="CUSTOMER">Customer</option>
              <option value="SELLER">Seller</option>
            
            </select>

            <button type="submit">Sign Up</button>
          </form>
        </div>

        {/* 🔴 Sign In */}
        <div className="form-container sign-in">
          <form onSubmit={handleLoginSubmit}>
            <h1>Sign In</h1>
            <span>Use your email and password</span>

            <input
              type="email"
              name="email"
              placeholder="Email"
              value={signInData.email}
              onChange={(e) => handleChange(e, setSignInData)}
              required
            />
            <input
              type="password"
              name="password"
              placeholder="Password"
              value={signInData.password}
              onChange={(e) => handleChange(e, setSignInData)}
              required
            />

            <a href="#">Forget Your Password?</a>
            <button type="submit">Sign In</button>
          </form>
        </div>

        {/* 🔁 Toggle Panels */}
        <div className="toggle-container">
          <div className="toggle">
            <div className="toggle-panel toggle-left">
              <h1>Welcome Back!</h1>
              <p>Enter your personal details to use all of site features</p>
              <button onClick={handleLoginClick}>Sign In</button>
            </div>
            <div className="toggle-panel toggle-right">
              <h1>Hello</h1>
              <p>
                Register with your personal details to use all of site features
              </p>
              <button onClick={handleRegisterClick}>Sign Up</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CustomerProfileForm;
