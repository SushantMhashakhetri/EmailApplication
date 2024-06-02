import React, { useState,useRef } from 'react';
import toast, { Toaster } from 'react-hot-toast';
import { sendEmail,sendEmailWithFile } from '../services/email.service';
import { Editor } from '@tinymce/tinymce-react';
function EmailSender() {
  const [emailData, setEmailData] = useState({
    to: '',
    subject: '',
    message: '',
  });

  const editorRef = useRef(null);

  const [sending,setSending] = useState(false);
  const handleFieldChange = (event, name) => {
    setEmailData({ ...emailData, [name]: event.target.value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if(emailData.to === '' || emailData.subject === '' || emailData.message === '') {
       toast.error('Invalid fields');
       return;
    }

    try {
      setSending(true);
        await sendEmail(emailData);
        toast.success('Email sent Successfully');
        setEmailData({
          to: '',
          subject: '',
          message: '',
        });
        editorRef.current.setContent('');
    } catch (e) {
        console.error(e);
        toast.error('Failed to send email')
    } finally {
      setSending(false);
    }
    console.log(emailData);
    // You can add additional logic here to send the email data to your backend or perform other actions.
  };

  return (
    <div className="w-full min-h-screen flex justify-center items-center">
      <div className="email_card md:w-2/3 w-full  p-4 bg-white dark:bg-gray-700 dark:text-white rounded-lg border dark:border-gray-600 shadow">
        <h1 className="text-grey-900 text-3xl">Email Sender Application</h1>
        <form onSubmit={handleSubmit} className="max-w-3xl mx-auto">
          <div className="my-5">
            <label htmlFor="email" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white text-left">
              To
            </label>
            <input
              value={emailData.to}
              onChange={(event) => handleFieldChange(event, 'to')}
              type="email"
              id="email"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="name@flowbite.com"
            />
          </div>
          <div className="mb-5">
            <label htmlFor="base-input" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white text-left">
              Subject
            </label>
            <input
              value={emailData.subject}
              onChange={(event) => handleFieldChange(event, 'subject')}
              type="text"
              id="base-input"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            />
          </div>
          <div className="mb-5">
            <label htmlFor="message" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white text-left">
              Your message
            </label>
            {/* <textarea
              value={emailData.message}
              onChange={(event) => handleFieldChange(event, 'message')}
              id="message"
              rows="4"
              className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Write your thoughts here..."
            ></textarea> */}


        <Editor
        onEditorChange={(event)=>{
          // console.log(editorRef.current.getContent())
          setEmailData({...emailData,message:editorRef.current.getContent()})
        }}
              apiKey='0zyp519qui9fc26lq477dkf1w8muadd51w4mbfh8xhuztsvb'
              onInit={(_evt, editor) => editorRef.current = editor}
              init={{
                plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage advtemplate ai mentions tinycomments tableofcontents footnotes mergetags autocorrect typography inlinecss markdown',
                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                tinycomments_mode: 'embedded',
                tinycomments_author: 'Author name',
                mergetags_list: [
                  { value: 'First.Name', title: 'First Name' },
                  { value: 'Email', title: 'Email' },
                ],
                ai_request: (request, respondWith) => respondWith.string(() => Promise.reject("See docs to implement AI Assistant")),
              }}
              initialValue="Welcome to TinyMCE!"
            />





          </div>
         {sending &&    <div className="loader flex-col gap-2 flex justify-center items-center">
          <div role="status">
              <svg aria-hidden="true" class="w-8 h-8 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="currentColor"/>
                  <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="currentFill"/>
              </svg>
              <span class="sr-only">Loading...</span>
          </div>
          <h1>Sending...</h1>
          </div>}
          <div className="button_container mt-5">
            <button
            disabled={sending}
              type="submit"
              className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800 float-left"
            >
              Send
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default EmailSender;
