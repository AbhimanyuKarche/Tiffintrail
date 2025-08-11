import React, { useState, useEffect } from "react";
import axios from "axios";

export default function ApprovedSellers() {
  const [sellers, setSellers] = useState([]);
  const [tiffins, setTiffins] = useState([]);
  const [selectedSeller, setSelectedSeller] = useState(null);

  // Fetch approved sellers
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/seller/approved") // your endpoint for approved sellers
      .then((res) => setSellers(res.data))
      .catch((err) => console.error(err));
  }, []);

  // Fetch tiffins for a seller
  const handleViewMenu = (sellerId, sellerName) => {
    setSelectedSeller(sellerName);
    axios
      .get(`http://localhost:8080/api/tiffins/seller/${sellerId}`) // your endpoint for tiffins by seller
      .then((res) => setTiffins(res.data))
      .catch((err) => console.error(err));
  };

  // Add to cart
  const handleAddToCart = (tiffinId) => {
    const token = localStorage.getItem("token"); // Assuming JWT is stored in localStorage
    axios
      .post(
        `http://localhost:8080/api/cart/add`,
        { tiffinId, quantity: 1 },
        { headers: { Authorization: `Bearer ${token}` } }
      )
      .then(() => alert("Added to cart!"))
      .catch((err) => console.error(err));
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Approved Sellers</h2>
      {sellers.map((seller) => (
        <div
          key={seller.id}
          style={{
            border: "1px solid #ccc",
            padding: "10px",
            margin: "10px",
          }}
        >
          <h3>{seller.name}</h3>
          <p>Email: {seller.email}</p>
          <button onClick={() => handleViewMenu(seller.id, seller.name)}>
            View Menu
          </button>
        </div>
      ))}

      {selectedSeller && (
        <>
          <h2 style={{ marginTop: "30px" }}>Menu for {selectedSeller}</h2>
          <div style={{ display: "flex", flexWrap: "wrap", gap: "20px" }}>
            {tiffins.length > 0 ? (
              tiffins.map((tiffin) => (
                <div
                  key={tiffin.id}
                  style={{
                    border: "1px solid #ddd",
                    borderRadius: "8px",
                    padding: "15px",
                    width: "250px",
                  }}
                >
                  <img
                    src={tiffin.imageUrl}
                    alt={tiffin.title}
                    style={{ width: "100%", borderRadius: "8px" }}
                  />
                  <h3>{tiffin.title}</h3>
                  <p>{tiffin.description}</p>
                  <p>
                    <strong>₹{tiffin.price}</strong>
                  </p>
                  <button onClick={() => handleAddToCart(tiffin.id)}>
                    Add to Cart
                  </button>
                </div>
              ))
            ) : (
              <p>No tiffins available for this seller.</p>
            )}
          </div>
        </>
      )}
    </div>
  );
}
