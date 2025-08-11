import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import AddToCartButton from "../Components/AddtoCartButton";

const AllTiffins = () => {
  const [tiffins, setTiffins] = useState([]);
  const [showQuantity, setShowQuantity] = useState({});
  const [quantities, setQuantities] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/tiffins/all")
      .then((res) => {
        setTiffins(res.data);
        const defaults = {};
        res.data.forEach((tiffin) => {
          defaults[tiffin.id] = 1;
        });
        setQuantities(defaults);
      })
      .catch((err) => {
        console.error("Error fetching tiffins:", err);
      });
  }, []);

  const handleQuantityChange = (id, value) => {
    setQuantities((prev) => ({
      ...prev,
      [id]: parseInt(value),
    }));
  };

  const handleAddClick = (id) => {
    setShowQuantity((prev) => ({
      ...prev,
      [id]: true,
    }));
  };

  const addToCart = async (id) => {
    const quantity = quantities[id] || 1;
    try {
      const token = localStorage.getItem("token");
      await axios.post(
        `http://localhost:8080/api/cart/add`,
        { tiffinId: id, quantity },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      alert("Tiffin added to cart!");
    } catch (error) {
      console.error("Error adding to cart:", error);
      alert("Failed to add to cart. Are you logged in?");
    }
  };

  return (
    <div className="p-6 max-w-5xl mx-auto">
      {/* Top Bar with View Cart */}
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-2xl font-bold">All Tiffins</h2>
        <button
          onClick={() => navigate("/cart")}
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          View Cart
        </button>
      </div>

      {tiffins.length === 0 ? (
        <p>No tiffins found.</p>
      ) : (
        <ul className="space-y-4">
          {tiffins.map((tiffin) => (
            <li
              key={tiffin.id}
              className="border p-4 rounded shadow-sm flex gap-4 items-start"
            >
              {/* Image */}
              {tiffin.imageUrl && (
                <img
                  src={tiffin.imageUrl}
                  alt={tiffin.title}
                  className="w-28 h-28 object-cover rounded"
                />
              )}

              {/* Details */}
              <div className="flex-1">
                <h3 className="text-lg font-semibold">{tiffin.title}</h3>
                <p className="text-sm text-gray-600">{tiffin.description}</p>
                <p className="text-sm font-medium">Price: ₹{tiffin.price}</p>
                <p className="text-sm text-gray-500">
                  Seller: {tiffin.sellerName} ({tiffin.sellerEmail})
                </p>
              </div>

              {/* Actions */}
              <AddToCartButton tiffinId={tiffin.id} />
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default AllTiffins;
