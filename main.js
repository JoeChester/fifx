var db = openDatabase('fifx', '1.0', 'Fifa Turnier DB', 2 * 1024 * 1024);

var resetted = true;

var pairings = [
    [1, 2],
    [3, 4],
    [5, 6],
    [7, 8],
    [9, 10],
    //----
    [1, 3],
    [2, 5],
    [4, 6],
    [7, 9],
    [8, 10],
    //----
    [1, 4],
    [2, 3],
    [5, 7],
    [6, 10],
    [8, 9],
    //----
    [3, 5],
    [4, 9],
    [2, 10],
    [1, 8],
    [6, 7],
    //----
    [4, 10],
    [5, 9],
    [2, 8],
    [1, 7],
    [3, 8],
    //----
    [2, 9],
    [4, 7],
    [5, 10],
    [3, 6],
    [1, 9],
    //----
    [4, 5],
    [1, 10],
    [6, 8],
    [3, 9],
    [4, 8],
    //----
    [2, 7],
    [5, 8],
    [6, 9],
    [3, 10],
    [1, 5],
    //----
    [3, 7],
    [2, 6],
    [7, 10],
    [4, 8],
    [1, 6]
]

function validNumber(n) {
    if(n === 0){
        return true;
    }
    if (isNaN(n) || n === "") {
        return false;
    } return true;
}

function shuffle(array) {
    var currentIndex = array.length, temporaryValue, randomIndex;
    while (0 !== currentIndex) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }
    return array;
}

function setup(cb) {
    db.transaction(function (tx) {
        tx.executeSql('CREATE TABLE IF NOT EXISTS FIFX (id unique, p1_id, p2_id, p1_name, p2_name, p1_score, p2_score, p1_points, p2_points)');
        console.log("TABLE CREATED!");
        cb();
    });
}

function reset() {
    setup(function () {
        db.transaction(function (tx) {
            tx.executeSql('DELETE FROM FIFX');
            console.log("DELETED ALL!");
            let id = 1;
            for (let pairing of pairings) {
                tx.executeSql('INSERT INTO FIFX (id, p1_id, p2_id) VALUES (?,?,?)', [id, pairing[0], pairing[1]]);
                id++;
            }
            console.log("CREATED PAIRINGS!");
            resetted = true;
        });
    })
}

function new_turn() {
    if (!resetted) {
        alert("Reset before creating a new Tournament!")
        return;
    }
    var player_names = [];
    for (var i = 1; i < 11; i++) {
        let name = document.getElementById("player_name_" + i).value;
        if (!name) {
            name = " ";
        }
        player_names.push(name);
    }
    player_names = shuffle(player_names);
    console.table(player_names);
    db.transaction(function (tx) {
        for (let i = 0; i < player_names.length; i++) {
            tx.executeSql('UPDATE FIFX SET p1_name=? where p1_id=?', [player_names[i], i + 1]);
            tx.executeSql('UPDATE FIFX SET p2_name=? where p2_id=?', [player_names[i], i + 1]);
        }
        console.log("PLAYERS INSERTED!");
    });

}

function load_turn() {

}

function save_game(id) {
    let p1_name = document.getElementById("game" + id + "_p1").innerText;
    let p2_name = document.getElementById("game" + id + "_p2").innerText;
    let p1_score = document.getElementById("game" + id + "_p1_score").value;
    let p2_score = document.getElementById("game" + id + "_p2_score").value;
    if (!validNumber(p1_score) || !validNumber(p2_score)) {
        alert("Ivalid Scores!");
        return;
    }
    let p1_points = 0;
    let p2_points = 0;
    if (p1_score == p2_score) {
        p1_points = 1;
        p2_points = 1;
    } else if (p1_score > p2_score) {
        p1_points = 3;
    } else if (p2_score > p1_score) {
        p2_points = 3;
    }
    db.transaction(function (tx) {
        tx.executeSql('UPDATE FIFX SET p1_score=?, p2_score=?, p1_points=?, p2_points=? where id=?', [p1_score, p2_score, p1_points, p2_points, id], function () {
            //cb
            refresh_game(id);
        });
    });
}

function refresh_game(id) {
    db.transaction(function (tx) {
        tx.executeSql('SELECT * FROM FIFX where id=?', [id], function (t, r) {
            let id = r.rows[0].id;
            let p1 = r.rows[0].p1_points;
            let p2 = r.rows[0].p2_points;
            console.log(id, p1, p2);
            if (!validNumber(p1) || !validNumber(p2)) {
                return;
            }
            if (p1 > p2) {
                document.getElementById("game" + id + "_p1").className = "winner";
                document.getElementById("game" + id + "_p2").className = "loser";
            } else if (p2 > p1) {
                document.getElementById("game" + id + "_p1").className = "loser";
                document.getElementById("game" + id + "_p2").className = "winner";
            } else if( p1 == p2 ){
                document.getElementById("game" + id + "_p1").className = "draw";
                document.getElementById("game" + id + "_p2").className = "draw";
            }
        });
    });
}