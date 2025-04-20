package com.Application.todolistapp.Contoller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExceptionCotroller {

    //function demonstrates how to use the responseentity to send response.
    @GetMapping("/api/v1/exception")
    public ResponseEntity<?> exceptionpractice() throws Exception {

        try{
            CustomException e = new CustomException("testexception");
            throw e;

        }
        catch(CustomException ce){
            Object HttpsStatus;
            ResponseEntity<?> responseentity = new ResponseEntity<>( ce.getMessage(), HttpStatus.BAD_REQUEST);
            return  responseentity;
        }
//        return new ResponseEntity<>("This is form getmapping exception api.", HttpStatus.OK);
    }

    //this function demonstrates how to seggregate exception handler functionality to handle exceptionHandler
    @GetMapping("/api/v1/exception1")
    public ResponseEntity<?> exceptionpractice1() throws Exception {


        try{
            CustomException e = new CustomException("Due to some business logic exception has raised.");
            throw e;

        }
        catch(CustomException ce){
//              ResponseEntity<?> responseentity=responsehandler1(ce);
//              return responseentity;
        }



       return new ResponseEntity<>("This is form getmapping exception api.", HttpStatus.OK);

    }


    //function and responsehandler1 function demonstrates how to use exception handler
    @GetMapping("/api/v1/exception2")
    public ResponseEntity<?> exceptionpractice2() throws Exception {

            CustomException e = new CustomException("Due to some business logic exception has raised from exception2.");

            throw e;


    }

    //this function and responsehandler2 will demonstrate how to handle multiple exceptions.
    @GetMapping("/api/v1/exception3")
    public ResponseEntity<?> multipleexceptions3() throws Exception{

        CustomException e = new CustomException("Due to some business logic exception has raised from exception3.");

        throw e;


    }

    //this function and responsehandler2 will demonstrate how to handle multiple exceptions.
    @GetMapping("/api/v1/exception4")
    public ResponseEntity<?> multipleexceptions4() throws Exception{

        IOException ie = new IOException("Due to some business logic exception has raised from exception4.");
        throw ie;

    }

    //function and responsehandler1 function demonstrates how to use exception handler.
//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<?> responsehandler1(Exception e){
//        System.out.println("This is from responsehandler");
//        ResponseEntity<?> responseentity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        return responseentity;
//    }

    //function and responsehandler2 function demonstrates how to use multiple exception handler
    @ResponseStatus(value=HttpStatus.BAD_GATEWAY, reason="IO exception responsestatus")
    @ExceptionHandler({CustomException.class,IOException.class})
    public ResponseEntity<?> responsehandler2(Exception e){
        System.out.println("This is from responsehandler2 form exception controller.");
        ResponseEntity<?> responseentity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        return responseentity;
    }


//commented above function because there are two handlers for IO exception which is giving ambiguious handler error.
  //  this function demonstrates when no exception handler can handle the exception how defaulterrorattribute will set response.
    @ExceptionHandler(IOException.class)
    public void responseWillBeCreatedByDefaultErrorAttribute(HttpServletResponse response, IOException e)throws IOException{
        System.out.println("this is from response handledby default attribute.");
         response.sendError(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }
    }

    class CustomException extends RuntimeException{

    String message;
    public CustomException(String msg){
        this.message = msg;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
         this.message = message;
    }

    }