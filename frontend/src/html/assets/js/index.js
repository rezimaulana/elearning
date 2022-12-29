const btnLoginAdmin = document.querySelector("#btnLoginAdmin")
const btnLoginInstructor = document.querySelector("#btnLoginInstructor")
const btnLoginStudent = document.querySelector("#btnLoginStudent")
const btnLogoutAdmin = document.querySelector("#btnLogoutAdmin")
const btnLogoutInstructor = document.querySelector("#btnLogoutInstructor")
const btnLogoutStudent = document.querySelector("#btnLogoutStudent")
const btnRegister = document.querySelector('#btnRegister')
const updateProfile = document.querySelector('#updateProfile')
const updatePhoto = document.querySelector('#updatePhoto')
const updatePassword = document.querySelector('#updatePassword')

const insertClasses = document.querySelector('#insertClasses')
const updateClasses = document.querySelector('#updateClasses')
const insertUsers = document.querySelector('#insertUsers')
const updateUsers = document.querySelector('#updateUsers')
const insertForum = document.querySelector('#insertForum')
const insertComment = document.querySelector('#insertComment')
const enrollClasses = document.querySelector('#enrollClasses')
const insertAttendanceExam = document.querySelector('#insertAttendanceExam')
const insertExamCollection = document.querySelector('#insertExamCollection')
const insertAttendanceQuiz = document.querySelector('#insertAttendanceQuiz')
const insertQuizCollection = document.querySelector('#insertQuizCollection')
const insertAttendanceLearning = document.querySelector('#insertAttendanceLearning')

insertExamMaterial = document.querySelector('#insertExamMaterial')
insertExamSchedule = document.querySelector('#insertExamSchedule')
insertQuizMaterial = document.querySelector('#insertQuizMaterial')
insertQuizSchedule = document.querySelector('#insertQuizSchedule')
insertLearningMaterial = document.querySelector('#insertLearningMaterial')
insertLearningSchedule = document.querySelector('#insertLearningSchedule')
approvAttendance = document.querySelector('#approvAttendance')
updateScore = document.querySelector('#updateScore')

if(btnLoginAdmin){
    btnLoginAdmin.addEventListener('click', () => {
        window.open('./pages/admin/home.html', '_self')
    })
}
if(btnLoginInstructor){
    btnLoginInstructor.addEventListener('click', () => {
        window.open('./pages/instructor/home.html', '_self')
    })
}
if(btnLoginStudent){
    btnLoginStudent.addEventListener('click', () => {
        window.open('./pages/student/home.html', '_self')
    })
}
if(btnLogoutAdmin){
    btnLogoutAdmin.addEventListener('click', () => {
        window.open('../../login.html', '_self')
    })
}
if(btnLogoutInstructor){
    btnLogoutInstructor.addEventListener('click', () => {
        window.open('../../login.html', '_self')
    })
}
if(btnLogoutStudent){
    btnLogoutStudent.addEventListener('click', () => {
        window.open('../../login.html', '_self')
    })
}
if(btnRegister){
    btnRegister.addEventListener('click', () => {
        window.open('./login.html', '_self')
    })
}
if(updateProfile){
    updateProfile.addEventListener('click', () => {
        alert('Updated');
    });
}
if(updatePhoto){
    updatePhoto.addEventListener('click', () => {
        alert('Updated');
    });
}
if(updatePassword){
    updatePassword.addEventListener('click', () => {
        alert('Updated');
    });
}

if(insertClasses){
    insertClasses.addEventListener('click', () => {
        window.open('./classes.html', '_self')
    })
}
if(updateClasses){
    updateClasses.addEventListener('click', () => {
        alert('Updated');
    });
}
if(insertUsers){
    insertUsers.addEventListener('click', () => {
        window.open('./users.html', '_self')
    })
}
if(updateUsers){
    updateUsers.addEventListener('click', () => {
        alert('Updated');
    });
}
if(insertForum){
    insertForum.addEventListener('click', () => {
        window.open('./forum.html', '_self')
    })
}
if(insertComment){
    insertComment.addEventListener('click', () => {
        window.open('./forum-detail.html', '_self')
    })
}
if(enrollClasses){
    enrollClasses.addEventListener('click', () => {
        window.open('./classes-enroll.html', '_self')
    })
}
if(insertAttendanceExam){
    insertAttendanceExam.addEventListener('click', () => {
        window.open('./examschedule.html', '_self')
    })
}
if(insertExamCollection){
    insertExamCollection.addEventListener('click', () => {
        window.open('./examschedule.html', '_self')
    })
}
if(insertAttendanceQuiz){
    insertAttendanceQuiz.addEventListener('click', () => {
        window.open('./quizschedule.html', '_self')
    })
}
if(insertQuizCollection){
    insertQuizCollection.addEventListener('click', () => {
        window.open('./quizschedule.html', '_self')
    })
}
if(insertAttendanceLearning){
    insertAttendanceLearning.addEventListener('click', () => {
        window.open('./learningschedule.html', '_self')
    })
}

if(insertExamMaterial){
    insertExamMaterial.addEventListener('click', () => {
        window.open('./exammaterial.html', '_self')
    })
}
if(insertExamSchedule){
    insertExamSchedule.addEventListener('click', () => {
        window.open('./examschedule.html', '_self')
    })
}
if(insertQuizMaterial){
    insertQuizMaterial.addEventListener('click', () => {
        window.open('./quizmaterial.html', '_self')
    })
}
if(insertQuizSchedule){
    insertQuizSchedule.addEventListener('click', () => {
        window.open('./quizschedule.html', '_self')
    })
}
if(insertLearningMaterial){
    insertLearningMaterial.addEventListener('click', () => {
        window.open('./learningmaterial.html', '_self')
    })
}
if(insertLearningSchedule){
    insertLearningSchedule.addEventListener('click', () => {
        window.open('./learningschedule.html', '_self')
    })
}
if(approvAttendance){
    approvAttendance.addEventListener('click', () => {
        alert('Updated');
    });
}
if(updateScore){
    updateScore.addEventListener('click', () => {
        alert('Updated');
    });
}