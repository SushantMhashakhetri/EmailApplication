import { customAxios } from "./helper";

export async function sendEmail(emailData){

   const result = (await customAxios.post('/email/send', emailData)).data;
   return result;

}

export async function sendEmailWithFile(emailData,formData){
   const result = (await customAxios.post('/email/send-with-file', emailData,formData, {
      headers: {
        "content-type": "multipart/form-data"
      }}));
   return result;

}