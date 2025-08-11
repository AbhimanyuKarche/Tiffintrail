import React, { useEffect, useState } from "react";
import axios from "axios";
import SellerProfileCard from "../Components/SellerProfileCard";
import TiffinList from "../Components/TiffinList";
import AddTiffinForm from "../Components/AddTiffinForm";
import { useNavigate } from "react-router-dom";

const SellerDashboard = () => {
  const navigate = useNavigate();
  const [profile, setProfile] = useState(null);
  const [tiffins, setTiffins] = useState([]);
  const [loading, setLoading] = useState(true);

  const token = localStorage.getItem("token"); // JWT stored after login

  useEffect(() => {
    const fetchData = async () => {
      try {
        const profileRes = await axios.get(
          "http://localhost:8080/api/seller/profile",
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        console.log(profileRes);

        const tiffinRes = await axios.get(
          "http://localhost:8080/api/tiffins/seller/my-tiffins",
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );

        setProfile(profileRes.data);
        setTiffins(tiffinRes.data);
        setLoading(false);
      } catch (err) {
        console.error("Failed to load data", err);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <p>Loading...</p>;

  return (
    <div className="p-4 space-y-6">
      <h1 className="text-2xl font-bold">Seller Dashboard</h1>

      <SellerProfileCard profile={profile} />
      {/* ✅ View Plans Button */}
      <button
        onClick={() => navigate("/sellerviewplans")}
        className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
      >
        View Your Created Plans
      </button>

      {profile.approvalStatus === "APPROVED" ? (
        <>
          <AddTiffinForm
            token={token}
            onSuccess={(newTiffin) => setTiffins([newTiffin, ...tiffins])}
          />
          <TiffinList tiffins={tiffins} />
        </>
      ) : (
        <p className="text-red-600 font-semibold">
          Your seller account is pending approval. Please wait for admin
          approval to add tiffins.
        </p>
      )}
    </div>
  );
};

export default SellerDashboard;
