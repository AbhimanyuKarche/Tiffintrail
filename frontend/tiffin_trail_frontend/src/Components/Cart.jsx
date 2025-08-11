import { useNavigate } from "react-router-dom";

const Cart = () => {
  const navigate = useNavigate();

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
    </div>
  );
};

export default Cart;
