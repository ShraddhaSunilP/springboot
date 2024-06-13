package com.luv2code.mvc.validation;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    //remove leading and trailing whitespace resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @GetMapping("/")
    public String showForm(Model theModel){
        // add data to the model so first item is the name, and second item is the value
        // The name is something important because we will actually use that name in our view page like our HTML form
        theModel.addAttribute("customer", new Customer());

        return "customer-form";
    }
    @PostMapping("/processForm")
    public String processForm(
            // Tell spring MVC to perform validation
            // @ModelAttribute("customer") this is the model name
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult) {
            // Binding result actully hold the results of the validation.

           if(theBindingResult.hasErrors()){
               return "customer-form";
           }
           else{
               return "customer-confirmation";
           }
    }
}
