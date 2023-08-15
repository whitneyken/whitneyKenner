
console.log("I changed this");

let national_parks = [];
let usStatesData;
let featuresArr = [];
window.onload = async function() {
  let text = await d3.text("output.json");
  let usData = await d3.json("clean_gdp.json");
  featuresArr = usData.features;
  console.log(featuresArr);


  for(let line of text.split('\n')){
    if(line){
      let obj = JSON.parse(line);
      //console.log(obj["index"])
      for(let park of obj["index"].split(", ")){
        national_parks.push(park);
      }
    }
  }
makeNPList();
//makeUSMap();

}


// function makeUSMap() {
//   let map = d3.select("#map");
//   let projection = d3.geoIdentity().fitSize([800, 500], usStatesData);
//
//   let path = d3.geoPath().projection(projection);
//
//   map.selectAll("path")
//     .data(featuresArr)
//     .enter()
//     .append("path")
//     .attr("d", path)
//     .style("fill", "lightpink")
//     .style("stroke", "white")
//     .style("stroke-width", 0.5);
// }
// function makeUSMap(){
//   //let path = d3.geoPath();
//   let map = d3.select("#map");
//   let projection = d3.geoAlbersUsa()
//     .fitSize([800, 500], {type:"featureCollection", features: []});
//
//   let path = d3.geoPath().projection(projection);
//
//   d3.json(usStatesData).then(function(data){
//     let featureCollection = d3.geoJson(data);
//     map.selectAll("path")
//     .data(featuresArr)
//     .enter()
//     .append("path")
//     .attr("d", path)
//     .style("fill", "lightpink")
//     .style("stroke", "white")
//     .style("stroke-width", 0.5)
//   });
//}
function makeBarGraph(){
  // Access the chart container
const chartContainer = d3.select("#map");

// Set the chart dimensions
const width = 400;
const height = 300;

// Create scales for x and y axes
const xScale = d3.scaleBand()
    .domain(data.map(d => d.name))
    .range([0, width])
    .padding(0.1);

const yScale = d3.scaleLinear()
    .domain([0, d3.max(data, d => d.value)])
    .range([height, 0]);

// Create the SVG element
const svg = chartContainer.append("svg")
    .attr("width", width)
    .attr("height", height);

// Create and append the bars
svg.selectAll(".bar")
    .data(data)
    .enter()
    .append("rect")
    .attr("class", "bar")
    .attr("x", d => xScale(d.name))
    .attr("y", d => yScale(d.value))
    .attr("width", xScale.bandwidth())
    .attr("height", d => height - yScale(d.value))
    .attr("fill", "steelblue");
}


function makeNPList(){
  let npNameDiv = d3.select("#npNameDiv");
  npNameDiv.selectAll('p')
    .data(national_parks)
    .join(
      enter => {
        enter.append('p')
        .text( (d, i)=> d)
        .on("click", handleNPClick)
      }
    );

}//end make NP list

function handleNPClick(event, data){
  //this <- DOM element
  let elem = d3.select(this);
  //getter
  let currentState = elem.classed("selected");
  //setter
  elem.classed("selected", !currentState);
}
