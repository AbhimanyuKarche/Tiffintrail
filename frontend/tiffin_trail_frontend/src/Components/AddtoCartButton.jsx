// src/components/AddToCartButton.jsx
import React, { useState } from "react";
import axios from "axios";

export default function AddToCartButton({ tiffinId, defaultQty = 1 }) {
  const [showQuantity, setShowQuantity] = useState(false);
  const [quantity, setQuantity] = useState(defaultQty);

  const handleQuantityChange = (value) => {
    setQuantity(parseInt(value) || 1);
  };

  const addToCart = async () => {
    try {
      const token = localStorage.getItem("token");
      if (!token) {
        alert("You must be logged in to add to cart.");
        return;
      }

      await axios.post(
        `http://localhost:8080/api/cart/add`,
        { tiffinId, quantity },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      alert("Tiffin added to cart!");
      setShowQuantity(false);
      setQuantity(defaultQty);
    } catch (error) {
      console.error("Error adding to cart:", error);
      alert("Failed to add to cart.");
    }
  };

  return (
    <div className="flex flex-col items-center gap-2">
      {!showQuantity ? (
        <button
          className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
          onClick={() => setShowQuantity(true)}
        >
          Add to Cart
        </button>
      ) : (
        <>
          <input
            type="number"
            min="1"
            className="w-16 border px-2 py-1 rounded"
            value={quantity}
            onChange={(e) => handleQuantityChange(e.target.value)}
          />
          <button
            className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
            onClick={addToCart}
          >
            Confirm
          </button>
        </>
      )}
    </div>
  );
}
