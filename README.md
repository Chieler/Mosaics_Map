![Framework](https://img.shields.io/badge/Framework-Spring-blue) ![Database](https://img.shields.io/badge/Database-MongoDB-white) ![Languages](https://img.shields.io/badge/Languages-Java,_Html,_Css,_Javascript-white)

# Mosaic Map

Mosaic Map is an interactive website to document your travels

# ![Link](https://mosaicsmap.com/)

<img src="https://raw.githubusercontent.com/Chieler/Mosaics_Map/main/src/main/resources/static/world.png" alt="World Map" width="200">

# About The Project:
I've always found websites that allow you to document your travel in an interactive way through maps to be hard to use and unintuitive. As someone who travels a lot this is an issue, so I decided to build my own! Everything is free and feel free to clone this repo if you want to contribute/use it for your own project!

# Setup:
```bash
#Clone the repo
git clone https://github.com/Chieler/Mosaics_Map/
cd Mosaics_Map
cd v1/src/main/resources/static/index.html
```
```html
#In index.html there should be this line of code (line 564):
const key = "kD3rI9asFF9re1naEevG";
#Change the key to your own key at Maptiler.com, as this key is a production key and restricted to use on the website only
```
```bash
cd v1/src/main/resources/application.properties
```
```properties
#Go to MongoDB Atlas and create a free cluster
#Add this line of code 
spring.data.mongodb.uri ={uri to connect to cluster}
```
And then your done! To run it simply go to V1Application.java and run it!

