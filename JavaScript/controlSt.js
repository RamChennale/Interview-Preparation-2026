// control statement

    // conditional statement
    // loop statement
    // jumping statement 

console.log("conditional")

let age= 10;
if(age>=18){
    console.log("Eligible to VOTE")
}else{
        console.log("NOT Eligible");
}

let a=50, b =4, c= 30;
if (a>b && a>c){
    console.log("a is greater then b and c")
}

let weekno =1;
switch(weekno)
{
    case 1: console.log('sunday');
        break;
    
        case 2: console.log('Mon');  break;
    
    case 3: console.log('Tue');
        break;

    case 4: console.log('Wed');  break;
    
    case 5: console.log('Thu');
        break;

    case 6: console.log('Fri');  break;
    
       case 1: console.log('sat');
        break;

    case 7: console.log('Mon');  break;
    default:
        console.log('Invalid WEEK');
}


