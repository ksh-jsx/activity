import React, { useState } from "react";
import { authService } from "fbase";
import IconButton from '@mui/material/IconButton';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import TextField from '@mui/material/TextField';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DatePicker from '@mui/lab/DatePicker';

const today = new Date();

const SignUpForm = () => {

  const [values, setValues] = useState({
    email: '',
    password: '',
    password2: '',
    name: '',
    birthday: null,
    showPassword: false,
  });
  const [error, setError] = useState("");

  
  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };

  const handleClickShowPassword = () => {
    setValues({
      ...values,
      showPassword: !values.showPassword,
    });
  };

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  const CheckEmail = (str) => {                   
     var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
     if(!reg_email.test(str)) {                            
          return false;         
     }                            
     else {                       
          return true;         
     }                            
  }             
  const onSubmit = async (event) => {
    event.preventDefault();
    if(faultCheck()){
      try {
        let data = await authService.createUserWithEmailAndPassword(
          values.email,
          values.password
        ).then(function(result) {
          return result.user.updateProfile({
            displayName: values.name
          })
        })        
      } catch (error) {
        setError(error.message);
        alert(error)
      }
    } 
  };

  const faultCheck = () => {
    let alertWord = null;
    let focusID = null;
    

    if(values.password.length<8){
      alertWord = '8자 이상의 비밀번호를 입력하세요'
      focusID = "outlined-adornment-password"
    }
    if(values.password != values.password2){
      alertWord = '비밀번호를 확인해주세요'
      focusID = "outlined-adornment-password"
    }
    if(!CheckEmail(values.email)){
      alertWord = '이메일 형식이 아닙니다'
      focusID = "emailBox"      
    }
    if(values.birthday == 'Invalid Date' || values.birthday.getTime()>today.getTime() || values.birthday.getFullYear()<1900){
      alertWord = '유효한 생년월일을 입력하세요'
      focusID = "birthdayBox"      
    }
    if(alertWord == null) 
      return true;
    else{
      alert(alertWord)
      document.getElementById(focusID).focus(); 
      return false
    }
  }
  
  return (
    <div>
      <form onSubmit={onSubmit} className="signUpForm">
          <TextField
            id="emailBox"
            name="email"
            type="text"
            label="이메일"            
            variant="outlined"            
            required
            value={values.email}
            autoComplete="off"
            onChange={handleChange('email')}
          />
          <FormControl variant="outlined">
            <InputLabel htmlFor="outlined-adornment-password">비밀번호</InputLabel>
            <OutlinedInput
              id="outlined-adornment-password"
              type={values.showPassword ? 'text' : 'password'}
              value={values.password}
              onChange={handleChange('password')}
              required
              minlength="8" maxlength="15"
              endAdornment={
                <InputAdornment position="end">
                  <IconButton
                    aria-label="toggle password visibility"
                    onClick={handleClickShowPassword}
                    onMouseDown={handleMouseDownPassword}
                    edge="end"
                  >
                    {values.showPassword ? <VisibilityOff /> : <Visibility />}
                  </IconButton>
                </InputAdornment>
              }
              label="비밀번호"
            />
          </FormControl>
          <FormControl variant="outlined">
            <InputLabel htmlFor="outlined-adornment-password">비밀번호 확인</InputLabel>
            <OutlinedInput
              id="outlined-adornment-password"
              type={values.showPassword ? 'text' : 'password'}
              value={values.password2}
              onChange={handleChange('password2')}
              required
              minlength="8" maxlength="15"
              label="비밀번호 확인"
            />
          </FormControl>
          <TextField
            name="name"
            label="이름"
            pattern="[가-힣]{3,7}"
            required            
            variant="outlined"
            value={values.name}
            autoComplete="off"
            onChange={handleChange("name")}
          />
          <LocalizationProvider dateAdapter={AdapterDateFns} >
            <DatePicker
              disableFuture
              label="생년월일"
              views={['year', 'month', 'day']}
              value={values.birthday}
              onChange={(newValue) => {
                setValues({ ...values, 'birthday': newValue });
              }}
              renderInput={(params) => <TextField {...params} autoComplete="off" id="birthdayBox"/>}
            />
          </LocalizationProvider>
        <input
          type="submit"
          value={"회원가입"}
        />
      </form>
    </div>
  );
};
export default SignUpForm;