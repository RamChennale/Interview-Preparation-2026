//CREATING OBJECT:

let person =
{
    fname : "Ram",
    lname : "chennale",
    age : 35
}

//accesing the obj 
console.log(person["fname"]);
console.log(person.lname); // 

//operation adding new pproprty 
person["height"] = 5.5;
console.log(person["height"]);
delete person["age"];
console.log(person.age); // 

console.log("----------------")
for(let x in person){
    console.log(x);// to get peorperty 
}

for(let x in person){
    console.log(person[x]);// to get peorperty value 
}

for(let x in person){ 
console.log(x + "  "+person[x]);
}


let employee = {
    employeeName : "ram",
    employeeID : 123,
    salary: 22332,
    bonus : function(){
         return ((this.salary * 10 )/100);
    }
};

console.log(employee["employeeName"]);
console.log(employee.bonus());