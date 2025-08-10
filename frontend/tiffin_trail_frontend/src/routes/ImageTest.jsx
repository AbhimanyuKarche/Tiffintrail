import React, { useState } from "react";

function CloudinaryTestUpload() {
  const [imageUrl, setImageUrl] = useState("");
  const [loading, setLoading] = useState(false);

  const handleUpload = async (e) => {
    const file = e.target.files[0];
    if (!file) return;

    setLoading(true);

    const formData = new FormData();
    formData.append("file", file);
    formData.append("upload_preset", "TiffinImages"); // unsigned preset
    formData.append("cloud_name", "dekra32n2");

    try {
      const res = await fetch(
        `https://api.cloudinary.com/v1_1/dekra32n2/image/upload`,
        {
          method: "POST",
          body: formData,
        }
      );
      const data = await res.json();
      setImageUrl(data.secure_url);
    } catch (err) {
      console.error("Upload failed", err);
    }

    setLoading(false);
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Cloudinary Upload Test</h2>
      <input type="file" onChange={handleUpload} />
      {loading && <p>Uploading...</p>}
      {imageUrl && (
        <div>
          <p>Uploaded Image:</p>
          <img src={imageUrl} alt="Uploaded" style={{ maxWidth: "300px" }} />
        </div>
      )}
    </div>
  );
}

export default CloudinaryTestUpload;
