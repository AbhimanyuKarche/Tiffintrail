import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <div className="flex items-center justify-between px-8 py-4 shadow-2xl text-gray-950 font-semibold">
      {/* Logo */}
      <div>
        <img
          className="w-[100px] h-[88px]"
          src={"src/assets/logo.png"}
          alt="Logo"
        />
      </div>

      {/* Navigation Links */}
      <div className="flex gap-10 text-2xl">
        <Link to="/" className="hover:text-green-500">
          Home
        </Link>
        <Link to="/about" className="hover:text-green-500">
          About
        </Link>
        <Link to="/join" className="hover:text-green-500">
          Join Us
        </Link>
        <Link to="/contact" className="hover:text-green-500">
          Contact Us
        </Link>
      </div>

      {/* Auth Buttons */}
      <div className="flex gap-4">
        {/* <Link
          to="/login"
          className="px-6 py-2 border border-green-500 text-green-500 rounded-full hover:bg-green-100 transition"
        >
          Login
        </Link> */}
        <Link
          to="/signup"
          className="px-6 py-2 bg-[#63AB45] text-white rounded-full hover:bg-green-600 transition"
        >
          Sign In
        </Link>
      </div>
    </div>
  );
};

export default Navbar;
