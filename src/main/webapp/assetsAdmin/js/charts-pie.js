/**
 * For usage, visit Chart.js docs https://www.chartjs.org/docs/latest/
 */
// console.log(2);
// const dataCheckIn=document.getElementById("dataCheckIn").value();
// console.log(dataCheckIn);

const pieConfig = {
  type: 'doughnut',
  data: {
    datasets: [
      {
        data: [1,6,2],
        /**
         * These colors come from Tailwind CSS palette
         * https://tailwindcss.com/docs/customizing-colors/#default-color-palette
         */
        backgroundColor: ['#0694a2', '#1c64f2', '#7e3af2'],
        label: 'Dataset 1',
      },
    ],
    labels: ['Late', 'On time', 'Check'],
  },
  options: {
    responsive: true,
    cutoutPercentage: 80,
    /**
     * Default legends are ugly and impossible to style.
     * See examples in charts.html to add your own legends
     *  */
    legend: {
      display: false,
    },
  },
}

// change this to the id of your chart element in HMTL
const pieCtx = document.getElementById('pie')
window.myPie = new Chart(pieCtx, pieConfig)
