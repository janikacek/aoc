/**
 * Created by Jana on 01.12.2016.
 */
var pathS = "L2, L5, L5, R5, L2, L4, R1, R1, L4, R2, R1, L1, L4, R1, L4, L4, R5, R3, R1, L1, R1, L5, L1, R5, L4, R2, L5, L3, L3, R3, L3, R4, R4, L2, L5, R1, R2, L2, L1, R3, R4, L193, R3, L5, R45, L1, R4, R79, L5, L5, R5, R1, L4, R3, R3, L4, R185, L5, L3, L1, R5, L2, R1, R3, R2, L3, L4, L2, R2, L3, L2, L2, L3, L5, R3, R4, L5, R1, R2, L2, R4, R3, L4, L3, L1, R3, R2, R1, R1, L3, R4, L5, R2, R1, R3, L3, L2, L2, R2, R1, R2, R3, L3, L3, R4, L4, R4, R4, R4, L3, L1, L2, R5, R2, R2, R2, L4, L3, L4, R4, L5, L4, R2, L4, L4, R4, R1, R5, L2, L4, L5, L3, L2, L4, L4, R3, L3, L4, R1, L2, R3, L2, R1, R2, R5, L4, L2, L1, L3, R2, R3, L2, L1, L5, L2, L1, R4";
var pathA = pathS.split(',');

for (var i = 1; i < pathA.length; i++) {
    pathA[i] = pathA[i].trim();
}
var visitedLocations = [];
var visitedAgainLocations = [];
var position = {x: 0, y: 0};
var heading = "N";

for (var i = 0; i < pathA.length; i++) {
    console.log(i + ": " + heading);
    saveVisitedLocationsAndChangeDirection(pathA[i]);
}

console.log("end position: ");
console.log(position);
var distance = Math.abs(position.x) + Math.abs(position.y);
console.log("end distance: ");
console.log(distance);
var visitedTwiceDistance = Math.abs(visitedAgainLocations[0].x) + Math.abs(visitedAgainLocations[0].y);
console.log("first location visited twice distance: ");
console.log(visitedAgainLocations);

function getDirection(command) {
    return command.slice(0,1);
}

function getSteps(command) {
    return command.slice(1) * 1;
}

function addStepsAndChangeDirection(command) {
    var direction = getDirection(command);
    var steps = getSteps(command);

    if (heading === "N") {
        if (direction === "L") {
            position.x = position.x - steps;
            console.log(position);
            heading = "W";
        } else if (direction === "R") {
            position.x = position.x + steps;
            console.log(position);
            heading = "E";
        }
    } else if (heading === "E") {
        if (direction === "L") {
            position.y = position.y + steps;
            console.log(position);
            heading = "N";
        } else if (direction === "R") {
            position.y = position.y - steps;
            console.log(position);
            heading = "S";
        }
    } else if (heading === "S") {
        if (direction === "L") {
            position.x = position.x + steps;
            console.log(position);
            heading = "E";
        } else if (direction === "R") {
            position.x = position.x - steps;
            console.log(position);
            heading = "W";
        }
    } else if (heading === "W") {
        if (direction === "L") {
            position.y = position.y - steps;
            console.log(position);
            heading = "S";
        } else if (direction === "R") {
            position.y = position.y + steps;
            console.log(position);
            heading = "N";
        }
    }
}

function saveVisitedLocationsAndChangeDirection(command) {
    var direction = getDirection(command);
    var steps = getSteps(command);

    if (heading === "N") {
        if (direction === "L") {
            for (var i = 1; i <= steps; i++) {
                visitedLocations.push({x: position.x--, y: position.y});
                if (alreadyVisited()) {
                    // console.log(visitedAgainLocations);
                    visitedAgainLocations.push(JSON.parse(JSON.stringify(position)));
                }
            }
            //position.x = position.x - steps;
            heading = "W";
        } else if (direction === "R") {
            for (var i = 1; i <= steps; i++) {
                visitedLocations.push({x: position.x++, y: position.y});
                if (alreadyVisited()) {
                    // console.log(visitedAgainLocations);
                    visitedAgainLocations.push(JSON.parse(JSON.stringify(position)));
                }
            }
            //position.x = position.x + steps;
            heading = "E";
        }
    } else if (heading === "E") {
        if (direction === "L") {
            for (var i = 1; i <= steps; i++) {
                visitedLocations.push({x: position.x, y: position.y++});
                if (alreadyVisited()) {
                    // console.log(visitedAgainLocations);
                    visitedAgainLocations.push(JSON.parse(JSON.stringify(position)));
                }
            }
            //position.y = position.y + steps;
            heading = "N";
        } else if (direction === "R") {
            for (var i = 1; i <= steps; i++) {
                visitedLocations.push({x: position.x, y: position.y--});
                if (alreadyVisited()) {
                    // console.log(visitedAgainLocations);
                    visitedAgainLocations.push(JSON.parse(JSON.stringify(position)));
                }
            }
            //position.y = position.y - steps;
            heading = "S";
        }
    } else if (heading === "S") {
        if (direction === "L") {
            for (var i = 1; i <= steps; i++) {
                visitedLocations.push({x: position.x++, y: position.y});
                if (alreadyVisited()) {
                    // console.log(visitedAgainLocations);
                    visitedAgainLocations.push(JSON.parse(JSON.stringify(position)));
                }
            }
            //position.x = position.x + steps;
            heading = "E";
        } else if (direction === "R") {
            for (var i = 1; i <= steps; i++) {
                visitedLocations.push({x: position.x--, y: position.y});
                if (alreadyVisited()) {
                    // console.log(visitedAgainLocations);
                    visitedAgainLocations.push(JSON.parse(JSON.stringify(position)));
                }
            }
            //position.x = position.x - steps;
            heading = "W";
        }
    } else if (heading === "W") {
        if (direction === "L") {
            for (var i = 1; i <= steps; i++) {
                visitedLocations.push({x: position.x, y: position.y--});
                if (alreadyVisited()) {
                    // console.log(visitedAgainLocations);
                    visitedAgainLocations.push(JSON.parse(JSON.stringify(position)));
                }
            }
            //position.y = position.y - steps;
            heading = "S";
        } else if (direction === "R") {
            for (var i = 1; i <= steps; i++) {
                visitedLocations.push({x: position.x, y: position.y++});
                if (alreadyVisited()) {
                    visitedAgainLocations.push(JSON.parse(JSON.stringify(position)));
                }
            }
            //position.y = position.y + steps;
            heading = "N";
        }
    }

    function alreadyVisited() {
        for (var i = 0; i < visitedLocations.length - 1; i++) {
            if (position.x === visitedLocations[i].x && position.y === visitedLocations[i].y) {
                return true;
            }
        }
        return false;
    }
}