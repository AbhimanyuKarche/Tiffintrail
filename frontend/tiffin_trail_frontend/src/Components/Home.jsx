import React from "react";
import Navbar from "./Navbar";

const Home = () => {
  return (
    <>
      <Navbar></Navbar>
      <div className="flex-col h-[calc(100vh-88px)]">
        <div className="flex flex-1/2 h-[calc(100vh-200px)]">
          <div className=" bg-[url(/src/assets/Isolation_Mode.png)] bg-no-repeat bg-[length:30%]  w-[55%] pt-30 pl-35 font-[Poppins] leading-[75px]">
            <h1 className="text-8xl font-bold">
              100% <span className="text-[#63AB45]">Homemade</span>
            </h1>

            <h1 className="text-8xl font-bold mt-2">Food</h1>
            <div className="text-gray-700">
              <p className="text-2xl mt-12">
                You can get the best homemade food from your
              </p>
              <p className="text-2xl">
                neighbourhood homes. The food will be full of soul, health, &
                taste.
              </p>
            </div>
            <div></div>
          </div>
          <div className="flex w-[45%] h-[100%] justify-center">
            <img
              className="w-[60%] h-[90%] object-cover"
              src={"./src/assets/herosection.jpg"}
              alt=""
            />
          </div>
        </div>
        <div className="w-full bg-amber-200"></div>

        <div className=" flex font-[Poppins] justify-center text-4xl font-bold">
          <h1>
            We DO NOT serve food from{" "}
            <span className="text-red-500">Restaurants!</span>
          </h1>
        </div>
      </div>
    </>
  );
};

export default Home;
