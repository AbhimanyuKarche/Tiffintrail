import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <>
      <div className="flex items-center shadow-2xl   text-gray-950 font-semibold">
        <div className="pl-100">
          <img
            className="w-[100px] h-[88px] "
            src={"src/assets/logo.png"}
            alt=""
          />
        </div>
        <div className="pl-30 ">
          <div className=" flex flex-row gap-20 text-2xl">
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
        </div>
      </div>
    </>
  );
};

export default Navbar;
