/*function trapSquence() {

	let data1 = 200
	let data2 = 7
	let len = 300
	let map = new Map();

	for (let i = 200; i <= 300; i++) 
	{
		if (data1 % data2 == 0 || data2 % data1 == 0)
		 {
			map.set(data1, data2)
			data1 +=1;
			data2 +=2;
         }
        else if (data1.toString().length > 1)
         {

			let flag = true;
			let arr = data1.toString().split("");
			while (flag) {

				let sum = 0;
				for (let j = 0; j < arr.length; j++)
				 {

					sum = +sum + +arr[j];

				 }
				 
				if (sum == data2 || sum % data2 == 0 || data2 % sum == 0)
				 {

					map.set(data1, data2)

					data1 +=1;

					data2 +=2;

					flag = false;

				}

				else {

					if (sum.toString().length < 2) {

						data1 +=1;

						if (data2 > 3) {

							data2 = data2 - 1;

						}

						flag = false;

					}

					else 
					{
						arr = sum.toString().split("");

					}
				}


			}

		}


		else {

			data1 +=1;

			if (data2 > 3) {
				data2 = data2 - 1;

			}
		}


	}

	for (let user of map) {
		document.getElementById("data").innerHTML += user + "  ";

	}

	document.getElementById("total").innerHTML = "total=" + map.size;

}*/

function trapSquence() 
{

    let data1 =200;
    let data2 =7;
    let len =300;
    let map = new Map();
    
    for (let i =200; i <=300; i++) 
    {
                var temp=data1;
                var addTemp=0;
                let flag = true;
                
              while (flag)
              { 
                   //sum the digit a if greater then 9
                    if(temp.toString().length > 1)
                    {
                         let arr=temp.toString().split("");
                         let sum=0;
              
                         for (let j = 0; j < arr.length; j++)
                         {
                            sum = +sum + +arr[j];

                          }
                      
                           addTemp=sum;
                     }
                     else
                      {
                         addTemp=data1;
                              
                      }
                      
                     //check condition for trap 
                 
                    if (data1 % data2 == 0 || data2 % data1 == 0||addTemp == data2 || addTemp % data2 == 0 || data2 % addTemp == 0)
                    {

                       map.set(data1, data2)
                       data1++;
                       data2 +=2;
                       flag = false;

                    }

                   else 
                   {
	                   // once again check sum the digit a if greater then 9
                       if (addTemp.toString().length<=1)
                       {

						data1 +=1;
						if (data2 > 3)
						{
							data2 = data2 - 1;

						}
						flag = false;

					   }
					   else 
					   {
						temp=addTemp;

					   }
                    
                }  
           }
     }

    for (let user of map)
     {
		document.getElementById("data").innerHTML += user + "  ";

	}
       document.getElementById("total").innerHTML = "total=" + map.size;

}




function binaryGen(value)
 {
	let data = document.getElementById("user").value;
	let y = data;
	let bin = "";
	let rem;
	let count = 0;

	while (data != 0) {
		rem = data % 2;

		data = parseInt(data / 2);

		bin = bin + rem;

		if (rem == 1)
		 {
			count+=rem;
		}

	}
	document.getElementById("binary").innerHTML = "The binary form of " + y + " = " + bin;

	document.getElementById("count").innerHTML = "Number of one's in binary of " + y + " = " + count;



}




function createBox()
 {
	let num = document.getElementById("num1").value;
	
	var pdiv = document.createElement("div");
	pdiv.setAttribute("id", "parent");
	pdiv.style.display = "flex";

	var test = document.getElementById("test")
	test.appendChild(pdiv);

	var cdiv1 = document.createElement("div");
	cdiv1.setAttribute("id", "childone");
	cdiv1.style.flex = "50%";

	var cdiv2 = document.createElement("div");
	cdiv2.setAttribute("id", "childtwo");
	cdiv2.style.flex = "50%";

	pdiv.append(cdiv1, cdiv2);

	var father = document.createElement("p")
	father.innerHTML = "Father";
	cdiv1.appendChild(father);


	for (let i = 0; i < num; i++) 
	{
		var child1 = document.createElement("input");
		child1.style.margin = "5px";
		child1.setAttribute("id", "a" + i);
		cdiv1.appendChild(child1);

	}

	var son = document.createElement("p")
	son.innerHTML = "Son";
	cdiv2.appendChild(son);

	for (let j = 0; j < num; j++)
	 {
		var child2 = document.createElement("input");
		child2.style.margin = "5px";
		child2.setAttribute("id", "b" + j)
		cdiv2.appendChild(child2);

	}


	var result = document.createElement("input");
	result.setAttribute("id", "result");
	result.style.margin = "5px";

	var sub = document.createElement("button");
	sub.innerHTML = "SUMBIT"
	test.append(result, sub);
	sub.onclick = function() {

		let num = document.getElementById("num1").value;

		var map = new Map();

		for (var i = 0; i < num; i++) 
		{
			var father = document.getElementById("a" + i).value;
			var son = document.getElementById("b" + i).value;
			var dataMap = map.get(father);

			 if (dataMap == undefined)
			 {
				dataMap = new Array();
				map.set(father, dataMap);

			 }
			dataMap.push(son);

		}

		let inp = document.getElementById("result").value;
		var arrData = map.get(inp);
		var count = 0;
		var resultArray = new Array();

		if (arrData != null) 
		{
			for (var j = 0; j < arrData.length; j++) 
			{
				if (map.get(arrData[j]) != null)
				 {
					var valueArray = map.get(arrData[j]);
					
					count += valueArray.length;
					
					for(var k=0;k<valueArray.length;k++)
					{
					
					      resultArray.push(valueArray[k]);
					 
					 }

				}

			}


		}


		var paradata = document.createElement("p");
		var dataPara = document.createElement("p");
		var myBody = document.getElementById("test");

		if (count == 0)
		 {

			paradata.innerHTML = "No grandChild";

		}
		else if (count == 1)
		 {
			paradata.innerHTML = "He has " + count + " grandChild";
			dataPara.innerHTML = "His name is" + resultArray;

		}
		else 
		{
			paradata.innerHTML = "He has " + count + " grandChildren";
			var resultData="";
			
			for(var l=0;l<resultArray.length;l++)
			{
				
				if(l<(resultArray.length-1))
				{ 
					
					
					resultData=resultData+resultArray[l]+",";
					
				}
				 else
				 {
					
					resultData=resultData.slice(0,-1)+" & "+resultArray[l]+".";
				    
				 }
				
			    
			}
			dataPara.innerHTML = "Their name's are " +resultData;

		}
		myBody.append(paradata, dataPara);

	}
}



































