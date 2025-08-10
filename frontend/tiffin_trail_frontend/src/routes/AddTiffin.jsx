import { useState } from "react";
import axios from "axios";

const AddTiffinForm = ({ token, onSuccess }) => {
  const [form, setForm] = useState({ name: "", description: "", price: "" });
  const [imageFile, setImageFile] = useState(null);
  const [previewUrl, setPreviewUrl] = useState("");
  const [uploading, setUploading] = useState(false);
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (!file) return;

    // ✅ Check file type
    if (!file.type.startsWith("image/")) {
      setError("Only image files are allowed.");
      return;
    }

    // ✅ Check file size (max 2MB)
    if (file.size > 2 * 1024 * 1024) {
      setError("Image size must be less than 2MB.");
      return;
    }

    setError("");
    setImageFile(file);
    setPreviewUrl(URL.createObjectURL(file));
  };

  const handleRemoveImage = () => {
    setImageFile(null);
    setPreviewUrl("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    if (!form.name || !form.description || !form.price) {
      setError("All fields are required.");
      return;
    }

    if (Number(form.price) <= 0) {
      setError("Price must be greater than zero.");
      return;
    }

    if (!imageFile) {
      setError("Please select an image.");
      return;
    }

    try {
      setUploading(true);

      // 1️⃣ Upload to Cloudinary
      const formData = new FormData();
      formData.append("file", imageFile);
      formData.append("upload_preset", "TiffinImages");

      const cloudinaryRes = await axios.post(
        "https://api.cloudinary.com/v1_1/dekra32n2/image/upload",
        formData
      );

      const imageUrl = cloudinaryRes.data.secure_url;

      // 2️⃣ Send to backend
      const res = await axios.post(
        "http://localhost:8080/api/tiffins/add",
        { ...form, imageUrl },
        { headers: { Authorization: `Bearer ${token}` } }
      );

      alert("Tiffin added successfully!");
      onSuccess(res.data);

      // Reset form
      setForm({ name: "", description: "", price: "" });
      handleRemoveImage();
    } catch (err) {
      console.error(err);
      setError("Failed to add tiffin. Please try again.");
    } finally {
      setUploading(false);
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="border p-4 rounded-md space-y-3 bg-white shadow-md"
    >
      <h3 className="text-lg font-bold">Add New Tiffin</h3>

      {error && <p className="text-red-500 text-sm">{error}</p>}

      <input
        className="border p-2 w-full rounded"
        placeholder="Tiffin Name"
        name="name"
        value={form.name}
        onChange={handleChange}
      />
      <textarea
        className="border p-2 w-full rounded"
        placeholder="Description"
        name="description"
        value={form.description}
        onChange={handleChange}
        rows={3}
      />
      <textarea
        className="border p-2 w-full rounded"
        placeholder="Description"
        name="description"
        value={form.description}
        onChange={handleChange}
        rows={3}
      />
      <textarea
        className="border p-2 w-full rounded"
        placeholder="Description"
        name="description"
        value={form.description}
        onChange={handleChange}
        rows={3}
      />
      <input
        className="border p-2 w-full rounded"
        placeholder="Price"
        name="price"
        type="number"
        min="1"
        value={form.price}
        onChange={handleChange}
      />

      {/* Image Upload */}
      {!imageFile ? (
        <input
          type="file"
          accept="image/*"
          onChange={handleImageChange}
          className="border p-2 w-full rounded"
        />
      ) : (
        <div className="space-y-2">
          <img
            src={previewUrl}
            alt="Preview"
            className="w-48 h-48 object-cover rounded"
          />
          <button
            type="button"
            onClick={handleRemoveImage}
            className="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded"
          >
            Remove Image
          </button>
        </div>
      )}

      <button
        type="submit"
        className={`px-4 py-2 rounded text-white ${
          uploading ? "bg-gray-500" : "bg-green-600 hover:bg-green-700"
        }`}
        disabled={uploading}
      >
        {uploading ? "Uploading..." : "Add Tiffin"}
      </button>
    </form>
  );
};

export default AddTiffinForm;
