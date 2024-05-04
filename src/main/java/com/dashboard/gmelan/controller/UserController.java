package com.dashboard.gmelan.controller;

import com.dashboard.gmelan.user.config.UserCreationForm;
import service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signUp(UserCreationForm userCreationForm) {
        return "signup_form";
    }


    @PostMapping("/signup")
    public String signup(Model model, @Valid UserCreationForm userCreationForm, BindingResult bindingResult) {
        model.addAttribute("userCreationForm", userCreationForm);

        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        // 비밀번호 확인이 일치하지 않는 경우
        if(!userCreationForm.getPassword().equals(userCreationForm.getPasswordValidation())) {
            bindingResult.rejectValue(
                    "passwordValidation",
                    "signupPasswordValidationFailed",
                    "비밀번호가 일치하지 않습니다."
            );

            return "signup_form";
        }

        try {
            userService.create(
                    userCreationForm.getUsername(),
                    userCreationForm.getEmail(),
                    userCreationForm.getPassword(),
                    "IN_APP"

            );
        } catch (DataIntegrityViolationException e) { // 이메일 주소 또는 사용자 ID가 중복된 경우
            System.err.println(Arrays.toString(e.getStackTrace()));
            bindingResult.reject("signupDuplicateValue", "이미 등록된 사용자 또는 이메일 주소입니다.");
            return "signup_form";
        } catch (Exception e) { // 기타 오류
            System.err.println(Arrays.toString(e.getStackTrace()));
            bindingResult.reject("signupFailed", "사용자를 등록하는 중 오류가 발생했습니다: " + e.getMessage());
            return "signup_form";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
