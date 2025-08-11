import React from "react";
import { useNavigate } from "react-router-dom";

const TiffinList = ({ tiffins }) => {
  const navigate = useNavigate();

  if (!tiffins.length) return <p>No tiffins yet.</p>;

  const handleCreatePlan = (tiffinId) => {
    navigate("/sellersubplan", { state: { tiffinId } });
  };

  return (
    <div>
      <h3 className="text-lg font-bold mb-2">Your Tiffins</h3>
      <ul className="space-y-3">
        {tiffins.map((tiffin) => (
          <li
            key={tiffin.id}
            className="border p-3 rounded-md flex items-start space-x-4"
          >
            {/* Show image if available */}
            {tiffin.imageUrl && (
              <img
                src={tiffin.imageUrl}
                alt={tiffin.name}
                className="w-24 h-24 object-cover rounded"
              />
            )}

            {/* Text content */}
            <div className="flex-1">
              <h4 className="font-semibold">{tiffin.name}</h4>
              <p className="text-gray-700">{tiffin.description}</p>
              <p>
                <strong>Price:</strong> ₹{tiffin.price}
              </p>
              <button
                onClick={() => handleCreatePlan(tiffin.id)}
                className="mt-2 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
              >
                Create Plan for this Tiffin
              </button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TiffinList;
