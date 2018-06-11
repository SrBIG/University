/*
   * created by: Buruchiv
   * group: 621701
*/
var A = [];
var B = [];
var G = [];
var H = [];
var D = [];
var C = [];
var p = 0;
var m = 0;
var q = 0
var n = 0;
var Knr = 0;
var Enr = 0;
var Dnr = 0;
var L = 0;
var sumTime = 0;
var multiplyTime = 0;
var comparisonTime = 0;
var tableGenerated = 0;
var multiplyAmount = 0;
var sumAmount = 0;
var comparisonAmount = 0;
var T1 = 0;
var Tn = 0;
var compareRange = 0;
var totalRang = 0;
var aftComma = 5;

//created by: Biruchov
function start() {
    p = document.getElementById('pNum').value;
    m = document.getElementById('mNum').value;
    q = document.getElementById('qNum').value;
    n = document.getElementById('nNum').value;
    
    sumTime = document.getElementById('sumTime').value;
    multiplyTime = document.getElementById('compositTime').value;
    comparisonTime = document.getElementById('compareTime').value;

    T1 = 0;
    Tn = 0;
    Knr = 0;
    Enr = 0;
    Dnr = 0;
    L = 0;
    totalRang = 2 * m * p * q;

    if (rightInput() == false)
        return;
        
    generateData();

    cMatrix();
    calculateEnr();
    calculateD();

    if (tableGenerated == 1) {
        var table = document.getElementById('mainTable');

        document.body.removeChild(table);
        tableGenerated = 0;

        var table = document.createElement("table");
        table.id = 'mainTable';
        table.border = '1';
        document.body.appendChild(table);
    }

    generateTable();
}

//created by: Biruchov
function rightInput() {
    if (p == "" || m == "" || q == "" || n == "" || sumTime == "" || comparisonTime == "" || multiplyTime == "") {
        alert("Заполните все поля.");
        return false;
    }

   if (!p.match(/^\d+$/) || !m.match(/^\d+$/) || !q.match(/^\d+$/) || !n.match(/^\d+$/) || !sumTime.match(/^\d+$/) || !comparisonTime.match(/^\d+$/) 
   || !multiplyTime.match(/^\d+$/) || /^0/.test(p) || /^0/.test(m) || /^0/.test(q) || /^0/.test(n) || /^0/.test(multiplyTime) 
   || /^0/.test(multiplyTime) || /^0/.test(sumTime)) {
        alert("Введите корректные значения.");
        return false;
    }

    sumTime = parseInt(sumTime);
    comparisonTime = parseInt(comparisonTime);
    multiplyTime = parseInt(multiplyTime);

    return true;
}

//created by: Biruchov
function generateData() {
    for (var i = 0; i < p; i++) {
        A[i] = new Array();
        for (var j = 0; j < m; j++) {
            A[i][j] = (2 * Math.random() - 1).toFixed(aftComma);
        }
    }

    for (var i = 0; i < m; i++) {
        B[i] = new Array();
        for (var j = 0; j < q; j++) {
            B[i][j] = (2 * Math.random() - 1).toFixed(aftComma);
        }
    }

    for (var i = 0; i < m; i++) {
        G[i] = new Array();
        for (var j = 0; j < p; j++) {
            G[i][j] = (2 * Math.random() - 1).toFixed(aftComma);
        }
    }
    
    for (var i = 0; i < q; i++) {
        H[i] = new Array();
        for (var j = 0; j < m; j++) {
            H[i][j] = (2 * Math.random() - 1).toFixed(aftComma);
        }
    }
}

//created by: Biruchov
function dMatrix() {
    D = [];
    var t = 0;
    for(var i = 0; i < p; i++) {
        D[i] = new Array();
        for (var j = 0; j < q; j++) {
            D[i][j] = new Array();
            for (var k = 0; k < m; k++) {
                D[i][j][k] = (A[i][k] * B[k][j]).toFixed(aftComma);

                L += 2 * multiplyTime;
                t += multiplyTime;
                multiplyAmount++;
            }
        }
    }
    T1 += t;
    Tn = Math.ceil(t/n);
    multiplyAmount = 0;
}

//created by: Biruchov
function cMatrix() {
    multiplyAmount = 0;
    sumAmount = 0;
    comparisonAmount = 0;

    dMatrix();
    for(var i = 0; i < p; i++) {
        C[i] = new Array();
        for (var j = 0; j < q; j++) {
            C[i][j] = calculateCij(i, j);
        }
    }
}

//created by: Biruchov
function calculateCij(i, j) {
    var Cij = 0;
	var Dk = [];
    for (var k = 0; k < D.length; k++) {
        Dk.push(parseFloat(D[k][i][j]));
    }
	Cij = compare(i, j, Dk);
    compareRange = 0;
    return Cij.toFixed(aftComma);
}

//created by: Biruchov
function compare(i, j, Dk) {
	var t = 0;
    for(var x = 0; x < m; x++) {
        t += comparisonTime;
        comparisonAmount++;
        L += 2 * comparisonTime * 2;
        compareRange += 2;      
        if(G[x][i] > H[j][x]) {
        	T1 += t;
        	Tn += Math.ceil(t/n);
			return composeArray(Dk, 2);
        }
    }
    T1 += t;
    Tn += Math.ceil(t/n);
	return sumArray(Dk, 2);
}

//created by: Biruchov
function sumArray(D, range) {
	var res;
	var num = D.length;
	var Ds = D;
	while(num>1){
		var t = 0;
		if (num % 2 === 1) {
			num++;
			Ds[num - 1] = 1;
		}
		for (var i = 0; i < num; i+=2) {
			Ds[i/2] = Ds[i] + Ds[i+1];
			sumAmount++;
			t += sumTime;
		}
		num /=2;
		T1 += t;
    	Tn += Math.ceil(t / n);
    	L += sumTime * sumAmount * range;
	}
	res = Ds[0];
	return res;
}

//created by: Biruchov
function composeArray(D, range) {
	var res;
	var num = D.length;
	var Ds = D;
	while(num>1){
		var t = 0;
		if (num % 2 === 1) {
			num++;
			Ds[num - 1] = 1;
		}
		for (var i = 0; i < num; i+=2) {
			Ds[i/2] = Ds[i] * Ds[i+1];
			multiplyAmount++;
			t += multiplyTime;
		}
		num /=2;
		T1 += t;
    	Tn += Math.ceil(t / n);
    	L += multiplyTime * multiplyAmount * range;
	}
	res = Ds[0];
	return res;
}

//created by: Biruchov
function calculateKnr() {
    Knr = T1 / Tn;
}

//created by: Biruchov
function calculateEnr() {
    calculateKnr();
    Enr = Knr / n;
}

//created by: Biruchov
function calculateD() {
    L = L / totalRang;
    Dnr = Tn / L;
}

//created by: Biruchov
function generateTable() {
    tableGenerated = 1;

    var table = document.getElementById('mainTable');
    var tableRow = document.createElement('tr');

    var matrix;
    var matrixRow;
    var matrixCell;
    var matrixTitle;

    var tableCell = document.createElement('td');
    matrix = document.createElement('table');
    tableCell.innerHTML = "<div class = 'nameMatrix'>A:</div>";

    for (var i = 0; i < p; i++) {
        matrixRow = document.createElement('tr');
        for (var j = 0; j < m; j++) {
            matrixCell = document.createElement('td');
            matrixCell.innerHTML = A[i][j];
            matrixRow.appendChild(matrixCell);
        }
        matrix.appendChild(matrixRow);
    }

    tableCell.appendChild(matrix);
    tableRow.appendChild(tableCell);
	table.appendChild(tableRow);
    
    tableRow = document.createElement('tr');

    tableCell = document.createElement('td');
    matrix = document.createElement('table');
    tableCell.innerHTML = "<div class = 'nameMatrix'>B:</div>";

    for (var i = 0; i < m; i++) {
        matrixRow = document.createElement('tr');
        for (var j = 0; j < q; j++) {
            matrixCell = document.createElement('td');
            matrixCell.innerHTML = B[i][j];
            matrixRow.appendChild(matrixCell);
        }
        matrix.appendChild(matrixRow);
    }
    tableCell.appendChild(matrix);
    tableRow.appendChild(tableCell);
    table.appendChild(tableRow);

    tableRow = document.createElement('tr');

    tableCell = document.createElement('td');
    matrix = document.createElement('table');
    tableCell.innerHTML = "<div class = 'nameMatrix'>G:</div>";

    for (var i = 0; i < m; i++) {
        matrixRow = document.createElement('tr');
        for (var j = 0; j < p; j++) {
            matrixCell = document.createElement('td');
            matrixCell.innerHTML = G[i][j];
            matrixRow.appendChild(matrixCell);
        }
        matrix.appendChild(matrixRow);
    }

    tableCell.appendChild(matrix);
    tableRow.appendChild(tableCell);
    table.appendChild(tableRow);
    
    tableRow = document.createElement('tr');

    tableCell = document.createElement('td');
    matrix = document.createElement('table');
    tableCell.innerHTML = "<div class = 'nameMatrix'>H:</div>";
  
    for (var i = 0; i < q; i++) {
        matrixRow = document.createElement('tr');
        for (var j = 0; j < m; j++) {
            matrixCell = document.createElement('td');
            matrixCell.innerHTML = H[i][j];
            matrixRow.appendChild(matrixCell);
        }
        matrix.appendChild(matrixRow);
    }
    tableCell.appendChild(matrix);
    tableRow.appendChild(tableCell);
    table.appendChild(tableRow);

    tableRow = document.createElement('tr');

    tableCell = document.createElement('td');
        matrix = document.createElement('table');
        tableCell.innerHTML = "<div class = 'nameMatrix'>C:</div>";
        for (var i = 0; i < p; i++) {
            matrixRow = document.createElement('tr');
            for (var j = 0; j < q; j++) {
                matrixCell = document.createElement('td');
                matrixCell.innerHTML = C[i][j];
                matrixRow.appendChild(matrixCell);
            }
            matrix.appendChild(matrixRow);
        }
        tableCell.appendChild(matrix);
        tableRow.appendChild(tableCell);
        table.appendChild(tableRow);
    
    	tableRow = document.createElement('tr');

        tableCell = document.createElement('td');
        tableCell.style.textAlign = 'left';
        tableCell.innerHTML = 
        	"<b>Tn:</b> " + Tn + "<br>" +
            "<b>T1:</b> " + T1 + "<br>" +
            "<b>Ky(n,r):</b> " + Knr.toFixed(aftComma) + "<br>" +
            "<b>e(n,r):</b>  " + Enr.toFixed(aftComma) + "<br>" +
            "<b>D:</b> " + Dnr.toFixed(aftComma) + "<br>" +
            "<b>r:</b> " + totalRang;
        tableRow.appendChild(tableCell);
        table.appendChild(tableRow);

        document.body.appendChild(table);
}