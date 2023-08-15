console.log("I changed this");

let parks = [];
let previousClick;
let npData;
let stateArray = [];
let stateToSvgMap = {};
let state;

window.onload = async function() {
    npData = await d3.csv("npdata.csv");
    stateNames = await d3.csv("names.csv")
    stateNames.forEach((state) => {
        stateArray.push(state.State)
    });
    console.log("MT STATE ARRAY: ", stateArray);

    makeDropdownMenu(npData);
}



const width = d3.select('#stateBox').node().getBoundingClientRect().width;
const height = d3.select('#stateBox').node().getBoundingClientRect().height;

const timeLineWidth = d3.select('#timeline').node().getBoundingClientRect().width;
const timeLineheight = d3.select('#timeline').node().getBoundingClientRect().height;

async function makeDropdownMenu() {

    function mydropDown(stateArray) {

        let dropDown = d3.select("#dropdown_container")
            .append("select")
            .attr("class", "selection")
            .attr("name", "state-list");
        var options = dropDown.selectAll("option")
            .data(stateArray)
            .enter()
            .append("option");
        options.text(function(d) {
            return d;
        });
        dropDown.on("change", function() {
            selectedState = this.value;
            console.log("Selected state:", stateToSvgMap[selectedState]);
            //selectStateOnMap(stateToSvgMap[selectedState]);
            makeMoneyList(stateToSvgMap[selectedState]);
            state.transition().style("fill", (d) => (d.properties.name === selectedState) ? "lightpink" : "green");

        });
    }
    mydropDown(stateArray);
    stateSet = null;
}


async function makeMoneyList(stateName) {

    d3.selectAll("p").remove();


    console.log("\n*\n*\n*\n*INSIDE MAKEMONEYLIST");
    console.log("Name we clicked on is...", stateName, "and the type is: ", typeof(stateName));
    //in clicked on sate, d is:  {type: 'Feature', id: '32', properties: {…}, geometry: {…}}
    let npNameDiv = d3.select("#npNameDiv");
    console.log("type of state name is: ", typeof(stateName));
    const parksInStateData = npData.filter(
        (row) => row.State == stateName.properties.name
    );
    console.log("the parks in this state are: ", parksInStateData);

    parkSet = new Set();
    parksInStateData.forEach((element) =>
        parkSet.add(element.Parks));

    parkSet.forEach((element) =>
        console.log("Unique park is...", element));

    npNameDiv.selectAll('p')
        .data(parkSet)
        .join(
            enter => {
                enter.append('p')
                    .text((d, i) => d)
                    .on("click", handleMoneyClick)
            }
        );

    parkSet = null;
} //end make NP list



function handleMoneyClick(event, data) {
    console.log("handle money click");
    console.log("event is...", event);
    console.log("data is...", data);
    //this <- DOM element
    let elem = d3.select(this);
    //getter

    d3.selectAll(".selected").classed("selected", false);

    window.console.log(d3.selectAll("selected"));

    let selected = d3.selectAll("selected");

    let currentState = elem.classed("selected");

    //setter
    elem.classed("selected", !currentState);
    window.console.log("data is: ", data);
    makeParkBar(elem, data);

}

async function makeParkBar(elem, selectedPark) {

    //d3.selectAll("g").remove();

    console.log("in make Park bar");
    console.log("elem is...", elem);
    console.log("the selected park is...", selectedPark);

    let parkData = npData.filter((row) => row.Parks === selectedPark);

    //x Scaling
    const x = d3
        .scaleBand()
        .domain(
            d3.groupSort(
                parkData,
                ([d]) => d.YearRaw,
                (d) => d.YearRaw
            )
        )
        .range([0, timeLineWidth - 0])
        .padding(0.35);

    //y Scaling
    const y = d3
        .scaleLinear()
        .domain([0, d3.max(parkData, (d) => d.Visitors)])
        .range([timeLineheight - 30, 30]);

    // grab the SVG container.
    const svg = d3.select("#timeline");

    // need g elemet for svg to work
    const g = svg.append("g");

    parkData.forEach((item, i) => {
        console.log(item.YearRaw);

    });


    // Add a rect for each bar
    const pBar = g
        .append("g")
        .attr("fill", "lightpink")
        .selectAll()
        .data(parkData)
        .join("rect")
        .attr("x", (d) => x(d.YearRaw)) // pass year into x defined above which creates the x axis
        .attr("width", x.bandwidth() * 1)
        .attr("y", (d) => y(d.Visitors)) // pass consumption value into y defined above that creates y axis
        // console.log("y(d.visitors is...", y(d.Visitors))
        .attr("height", (d) => y(0) - y(d.Visitors));


    svg
        .append("g")
        .style("font-size", "11px")
        .attr("transform", `translate(0,${timeLineheight - 30})`)
        .call(d3.axisBottom(x).tickSizeOuter(0))
        .call((g) => g.attr("text-anchor", "start"));


    // Add the y-axis, and remove the domain line.
    svg
        .append("g")
        .attr("transform", `translate(${40},0)`)
        .call(d3.axisLeft(y).tickFormat((y) => y.toFixed(30)))
        .call((g) => g.select(".domain").remove())

    // this is just for the y axis label
    svg
        .append("text")
        .attr("transform", "rotate(-65)")
        .attr("x", "-30%")
        .attr("y", "-5")
        .style("transform", "rotate(-90deg)")
        .attr("fill", "currentColor")
        .attr("text-anchor", "start")
        .text("Visitors");

}
//My attempt to fix the zoom lol


//***************************************************************************

async function loadStates(stateJson) {
    const path = d3.geoPath();


    const zoom = d3.zoom()
        .scaleExtent([1, 8])
        .on("zoom", zoomed);

    console.log("INSIDE LOADS FUNCTION")

    let states = await d3.json(stateJson);

    //now grab svg for state box
    const stateBox = d3.select('#stateBox')
        .on("click", reset);


    const g = stateBox.append("g");


    state = g.append("g")
        .attr("fill", "green")
        .attr("cursor", "pointer")
        .selectAll("path")
        .data(topojson.feature(states, states.objects.states).features)
        .join("path")
        .on("click", clicked)
        .attr("d", path);

    state.append("title")
        .text(d => {
            stateName = d.properties.name;
            return d.properties.name
        });
    const stateSvg = g;


    g.append("path")
        .attr("fill", "none")
        .attr("stroke", "white")
        .attr("stroke-linejoin", "round")
        .attr("d", path(topojson.mesh(states, states.objects.states, (a, b) => a !== b)));

    stateBox.call(zoom);

    // Save the SVG element for each state as they are created
    state.each(function(d) {
        const stateName = d.properties.name;
        console.log("The state name is: ", stateName, " and the state svg is: ", state);
        stateToSvgMap[stateName] = d; // 'this' refers to the SVG element
    });

    function reset() {
        state.transition().style("fill", null);
        stateBox.transition().duration(750).call(
            zoom.transform,
            d3.zoomIdentity,
            d3.zoomTransform(stateBox.node()).invert([width / 2, height / 2])
        );
    }


    function clicked(event, d) {
        console.log("inside CLICKED ON A STATE FUNCTION");
        //console.log("state we clicked on is...", d.properties.name);
        console.log("this", d)

        const [
            [x0, y0],
            [x1, y1]
        ] = path.bounds(d);
        event.stopPropagation();
        state.transition().style("fill", null);
        d3.select(this).transition().style("fill", "lightpink");
        stateBox.transition().duration(750).call(
            zoom.transform,
            d3.zoomIdentity
            .translate(width / 2, height / 2)
            .scale(Math.min(8, 0.9 / Math.max((x1 - x0) / width, (y1 - y0) / height)))
            .translate(-(x0 + x1) / 2, -(y0 + y1) / 2),
            d3.pointer(event, stateBox.node())
        );
        makeMoneyList(d);
        selectStateInDropdown(d.properties.name);
    }

    function zoomed(event) {

        const {
            transform
        } = event;
        console.log(event);
        g.attr("transform", transform);
        g.attr("stroke-width", 1 / transform.k);
    }

    return stateBox.node();
}


// function selectStateOnMap(selectedState) {
//
//     console.log("inside CLICKED ON A STATE FUNCTION");
//     //console.log("state we clicked on is...", d.properties.name);
//     console.log("this", selectedState);
//     d3.selectAll("g path").transition().style("fill", "green");
//     d3.select(selectedState).transition().style("fill", "lightpink");
// }

function selectStateInDropdown(stateName) {
    const dropDown = d3.select(".selection");
    dropDown.property("value", stateName);
}


loadStates('states.json');
