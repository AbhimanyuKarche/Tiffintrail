const SellerProfileCard = ({ profile }) => {
  return (
    <div className="border p-4 rounded-md shadow-md">
      <h2 className="text-xl font-semibold">Seller Profile</h2>
      <p>
        <strong>Business Name:</strong> {profile.businessName}
      </p>
      <p>
        <strong>Phone:</strong> {profile.phoneNumber}
      </p>
      <p>
        <strong>Address:</strong> {profile.address}
      </p>
      <p>
        <strong>Status:</strong>{" "}
        <span
          className={
            profile.approvalStatus === "APPROVED"
              ? "text-green-600"
              : "text-yellow-600"
          }
        >
          {profile.approvalStatus}
        </span>
      </p>
    </div>
  );
};

export default SellerProfileCard;
