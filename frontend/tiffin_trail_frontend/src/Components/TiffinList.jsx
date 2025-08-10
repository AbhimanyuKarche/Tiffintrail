const TiffinList = ({ tiffins }) => {
  if (!tiffins.length) return <p>No tiffins yet.</p>;

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
            <div>
              <h4 className="font-semibold">{tiffin.name}</h4>
              <p className="text-gray-700">{tiffin.description}</p>
              <p>
                <strong>Price:</strong> ₹{tiffin.price}
              </p>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TiffinList;
