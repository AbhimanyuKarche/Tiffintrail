import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import CardActionArea from "@mui/material/CardActionArea";
import Explore from "../assets/Explore.jpg";
import favSeller from "../assets/favSeller.png";
import { useNavigate } from "react-router-dom";
export default function ActionAreaCard() {
  const navigate = useNavigate();
  return (
    <div className="w-full h-screen bg-gradient-to-r from-[#c9d6ff]  to-[#e2e2e2] flex items-center gap-10 ">
      <div className="ml-120">
        <Card sx={{ maxWidth: 400, borderRadius: 10 }}>
          <CardActionArea onClick={() => navigate("/tiffins")}>
            <CardMedia
              component="img"
              height="140"
              image={Explore}
              alt="green iguana"
            />
            <CardContent>
              <Typography
                gutterBottom
                variant="h5"
                component="div"
                align="center"
              >
                Explore Tiffins from All Sellers
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
        <CardActionArea>
          <CardMedia
            component="img"
            height="140"
            image={favSeller}
            alt="green iguana"
          />
          <CardContent>
            <Typography
              gutterBottom
              variant="h5"
              component="div"
              align="center"
            >
              Order From You Favourite Seller
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
}
