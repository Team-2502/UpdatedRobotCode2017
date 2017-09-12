// const fs = require("browserify-fs");
// const fs = require("fs");

var process_data = function(data)
{
  let result = [];
  for(let j = 0; j < data.length; j++)
  {
    let line = data[j].split(" ");
    result.push({
      pos: line[0],
      vel: line[1],
      acc: line[2],
      jerk: line[3],
      hdg: line[4],
      dt: line[5],
      x: line[6],
      y: line[7],
    });
  }
  return result;
}

var avg = function(a, b) { return (a+b)/2 };

var instantiate_chart = function(name, text_data)
{
  document.getElementById("xy_div").innerHTML = "<canvas id=\"xy_chart\" width=400 height=200></canvas>";
  document.getElementById("left_pvaj_div").innerHTML = "<canvas id=\"left_pvaj_chart\" width=400 height=200></canvas>"
  document.getElementById("right_pvaj_div").innerHTML = "<canvas id=\"right_pvaj_chart\" width=400 height=200></canvas>"
  document.getElementById("hdg_div").innerHTML = "<canvas id=\"hdg_chart\" width=400 height=200></canvas>"

  text_data = text_data[name];

  let hdg_chart = document.getElementById("hdg_chart");
  let xy_chart = document.getElementById("xy_chart");

  let left_data = [];
  let right_data = [];
  let hdg_data = [];

  let left_pos_data = [];
  let left_vel_data = [];
  let left_acc_data = [];
  let left_jerk_data = [];

  let right_pos_data = [];
  let right_vel_data = [];
  let right_acc_data = [];
  let right_jerk_data = [];


  for(let i = 0; i < text_data["left"].length; i++)
  {
    left_data.push({x: text_data["left"][i]["x"], y: text_data["left"][i]["y"]});
    right_data.push({x: text_data["right"][i]["x"], y: text_data["right"][i]["y"]});

    left_pos_data.push({x: text_data["left"][i]["pos"], y: i * text_data["left"][i][dt]});
    left_pos_data.push({x: text_data["left"][i]["vel"], y: i * text_data["left"][i][dt]});
    left_pos_data.push({x: text_data["left"][i]["acc"], y: i * text_data["left"][i][dt]});
    left_pos_data.push({x: text_data["left"][i]["jerk"], y: i * text_data["left"][i][dt]});

    right_pos_data.push({x: text_data["right"][i]["pos"], y: i * text_data["right"][i][dt]});
    right_pos_data.push({x: text_data["right"][i]["vel"], y: i * text_data["right"][i][dt]});
    right_pos_data.push({x: text_data["right"][i]["acc"], y: i * text_data["right"][i][dt]});
    right_pos_data.push({x: text_data["right"][i]["jerk"], y: i * text_data["right"][i][dt]});

    hdg_data.push({x: text_data["left"][i]["hdg"], y: i * text_data["left"][i][dt]})
  }

  console.log(left_data);
  console.log(right_data);

  let left_pvaj_graph = new Chart(hdg_chart, {
    type: 'scatter',
    data: {
      datasets: [
        {
          label: 'Position (ft)',
          data: left_data,
        }
      ]
    },
    options: {
      title: "Motion information of left side",
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero:true
          }
        }]
      }
    }
  });
  let right_pvaj_graph = new Chart(hdg_chart, {
    type: 'scatter',
    data: {
      datasets: [
        {
          label: 'Heading in radians',
          data: left_data,
        }
      ]
    },
    options: {
      title: "Motion information of right side",
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero:true
          }
        }]
      }
    }
  });

  let hdgdiv_chart = new Chart(hdg_chart, {
    type: 'scatter',
    data: {
      datasets: [
        {
          label: 'Heading in radians',
          data: left_data,
        }
      ]
    },
    options: {
      title: "Angle of the robot over time",
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero:true
          }
        }]
      }
    }
  });

  let xydiv_chart = new Chart(xy_chart, {
    type: 'scatter',
    data: {
      datasets: [
        {
          label: 'Left Side',
          data: left_data,
        },
        {
          label: "Right Side",
          data: right_data,
        }
      ]
    },
    options: {
      title: "Path of the robot (units are in feet)",
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero:true
          }
        }]
      }
    }
  });


}

var process_text = function()
{

  var text_data = {};

  let file = document.getElementById("stuffs").value;
  let file_contents = file.toString().split("\n");
  let name = file_contents[0];
  text_data[name] = {"left": [], "right": []};

  let points = file_contents[1];


  file_contents.shift(); // remove empty
  file_contents.shift(); // remove name
  file_contents.shift(); // remove number


  let left = file_contents.slice(0, points);
  let right = file_contents.slice(points);

  text_data[name]["left"] = process_data(left);
  text_data[name]["right"] = process_data(right);

  instantiate_chart(name, text_data);


}
//