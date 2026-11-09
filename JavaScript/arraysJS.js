let cars = ["abs","bmw", "maruth"];

let bikes  = Array("honda", "hero", "TVS");

console.log(cars);
console.log(bikes)
console.log(bikes[1]);
bikes[0] ="BUKA";
console.log(bikes[0]);

let arr1 = [11,"ram", 1.1, true];

let person = {
    name : "rJ",
    age : 30
};

arr1 = [person];
console.log("-------------")
console.log(arr1)
console.log(arr1.length);

let fruit = ["ban", "app", "kela"];

for(let i =0;  i<fruit.length; i++ ){
    console.log(fruit[i]);
}

// array loopoing for/of loop 
for(elements of fruit){
    console.log(elements);
}

console.log(fruit.pop());
console.log(fruit.push("KIVI"));
console.log(fruit);

let date = new Date();
console.log(date);

console.log(date.getDate());
console.log(date.getMonth());
console.log(date.getFullYear());
