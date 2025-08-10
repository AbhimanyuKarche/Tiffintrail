import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import CardActionArea from "@mui/material/CardActionArea";
import registerImage from "../assets/Register.jpg";
import viewDash from "../assets/ViewDash.jpg";
import { useNavigate } from "react-router-dom";
const SellerMainPage = () => {
  const navigate = useNavigate();
  return (
    <div className="w-full h-screen bg-gradient-to-r from-[#c9d6ff]  to-[#e2e2e2] flex items-center gap-10 ">
      <div className="ml-120">
        <Card sx={{ maxWidth: 400, borderRadius: 10 }}>
          <CardActionArea onClick={() => navigate("/sellerRegistration")}>
            <CardMedia
              component="img"
              height="140"
              image={registerImage}
              alt="green iguana"
            />
            <CardContent>
              <Typography
                gutterBottom
                variant="h5"
                component="div"
                align="center"
              >
                Register as Seller
              </Typography>
              <Typography
                variant="body2"
                sx={{ color: "text.secondary" }}
              ></Typography>
            </CardContent>
          </CardActionArea>
        </Card>
      </div>
      <Card sx={{ maxWidth: 400, borderRadius: 10 }}>
        <CardActionArea onClick={() => navigate("/sellerDashboard")}>
          <CardMedia
            component="img"
            height="140"
            image={viewDash}
            alt="green iguana"
          />
          <CardContent>
            <Typography
              gutterBottom
              variant="h5"
              component="div"
              align="center"
            >
              View Seller Dashboard
            </Typography>
            <Typography
              variant="body2"
              sx={{ color: "text.secondary" }}
            ></Typography>
          </CardContent>
        </CardActionArea>
      </Card>
    </div>
  );
};

export default SellerMainPage;
