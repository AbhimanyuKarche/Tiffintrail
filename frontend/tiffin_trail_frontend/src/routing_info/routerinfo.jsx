import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "../Components/Home";
import About from "../routes/About";
import Join from "../routes/Join";
import Signup from "../routes/Signup";

import SellerRegistration from "../routes/SellerRegistration";
import Login from "../routes/LoginTesting";
import SellerAddTiffinForm from "../routes/AddTiffin";
import CustomerProfileForm from "../routes/CustomerProfile";
import AllTiffins from "../routes/Viewtiffins";
import Cart from "../routes/Cart";
import CheckoutPage from "../routes/CheckoutPage ";
import SellerMainPage from "../routes/SellerMainPage";
import SellerDashboard from "../routes/SellerDashboard";
import AdminDashboard from "../routes/AdminDashboard";
import CustomerDashboard from "../routes/CustomerDashboard";
import FavSellerPage from "../routes/FavSellerPage";
import FavtiffinListing from "../routes/FavtiffinListing";
import SellersubPlan from "../routes/SellerSubPlan";
import UserSubPage from "../routes/UserSubPage";
import ViewPlans from "../routes/SellerViewPlans";
import ImageTest from "../routes/ImageTest";

const routerinfo = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/join" element={<Join />} />

        <Route path="/signup" element={<Signup />} />

        <Route path="/sellerRegistration" element={<SellerRegistration />} />
        <Route path="/login" element={<Login />} />
        <Route path="/addTiffin" element={<SellerAddTiffinForm />} />
        <Route path="/customerRegistration" element={<CustomerProfileForm />} />
        <Route path="/tiffins" element={<AllTiffins />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/checkout" element={<CheckoutPage />} />
        <Route path="/sellermpage" element={<SellerMainPage />} />
        <Route path="/sellerDashboard" element={<SellerDashboard />} />
        <Route path="/adminDashboard" element={<AdminDashboard />} />
        <Route path="/customerDashboard" element={<CustomerDashboard />} />
        <Route path="/favSellerPage" element={<FavSellerPage />} />
        <Route path="/favtiffinListing" element={<FavtiffinListing />} />
        <Route path="/usersubpage" element={<UserSubPage />} />
        <Route path="/sellersubplan" element={<SellersubPlan />} />
        <Route path="/sellerviewplans" element={<ViewPlans />} />
      </Routes>
    </BrowserRouter>
  );
};

export default routerinfo;
