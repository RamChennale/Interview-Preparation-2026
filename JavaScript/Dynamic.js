document.getElementById("demo1").innerHTML= "JS Dynamic messge";
document.getElementById("demo2").innerHTML = "world";
document.getElementById("myBtn").onclick = displayDate;

function displayDate(){
    document.getElementById("myBtn").innerHTML = Date();
}