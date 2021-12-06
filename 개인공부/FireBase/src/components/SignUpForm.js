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

const SignUpForm = () => {

  const [values, setValues] = useState({
    email: '',
    password: '',
    password2: '',
    name: '',
    birthday: '',
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
    if(CheckEmail(values.email)){
      if(values.password == values.password2){
        try {
          let data = await authService.createUserWithEmailAndPassword(
            values.email,
            values.password
          );
          console.log(data);
        } catch (error) {
          setError(error.message);
          alert(error)
        }
      }
      else{ alert('비밀번호를 확인해주세요') }
    }
    else{ alert('이메일 형식이 아닙니다') }
  };
  
  return (
    <div>
      <form onSubmit={onSubmit} className="signUpForm">
          <TextField
            name="email"
            label="이메일"            
            variant="outlined"
            required
            value={values.email}
            onChange={handleChange('email')}
            autoComplete="off"
          />
          <FormControl variant="outlined">
            <InputLabel htmlFor="outlined-adornment-password">비밀번호</InputLabel>
            <OutlinedInput
              id="outlined-adornment-password"
              type={values.showPassword ? 'text' : 'password'}
              value={values.password}
              onChange={handleChange('password')}
              required
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
              label="비밀번호 확인"
            />
          </FormControl>
          <TextField
            name="name"
            type="text"
            variant="outlined"
            placeholder="이름"
            required
            value={values.name}
            autoComplete="off"
            onChange={handleChange("name")}
          />
          <TextField
            name="birthday"
            type=""
            variant="outlined"
            placeholder="생년월일"
            required
            value={values.birthday}
            autoComplete="off"
            onChange={handleChange("birthday")}
          />
        <input
          type="submit"
          value={"회원가입"}
        />
      </form>
    </div>
  );
};
export default SignUpForm;