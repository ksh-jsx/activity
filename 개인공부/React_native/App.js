import React, { useState, useEffect } from "react";
import { Alert } from "react-native";
import Loading from "./Loading";
import * as Location from "expo-location";
import axios from "axios";
import Weather from "./Weather";

const API_KEY = "c9159c297c3a07125437ffc945d501ef";

function App() {
  const [isLoading, setIsLoading] = useState(true);
  const [condition, setCondition] = useState();
  const [temp, setTemp] = useState();

  const getWeather = async (latitude, longitude) => {
    const {
      data: {
        main: { temp },
        weather
      }
    } = await axios.get(
      `http://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&APPID=${API_KEY}&units=metric`
    );
    setCondition(weather[0].main);
    setTemp(temp);
    setIsLoading(false);
  };

  const getLocation = async () => {
    try {
      await Location.requestForegroundPermissionsAsync();
      const {
        coords: { latitude, longitude }
      } = await Location.getCurrentPositionAsync();
      getWeather(latitude, longitude);
    } catch (error) {
      Alert.alert("Can't find you.", "So sad");
    }
  };

  useEffect(() => {
    getLocation();
  }, [condition,temp]);

  return (
    <>
      {isLoading ? (
        <Loading />
      ) : (
          <Weather temp={Math.round(temp)} condition={condition} />
      )}
    </>
  );
}

export default App;
