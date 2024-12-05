import { Component } from '@angular/core';
import {Chart, registerables} from 'chart.js';
@Component({
  selector: 'app-dashboardrecruiter',
  templateUrl: './dashboardrecruiter.component.html',
  styleUrl: './dashboardrecruiter.component.css'
})
export class DashboardrecruiterComponent {
  constructor() {
    Chart.register(...registerables);
  }
  ngOnInit(): void {
    this.createResponseChart();
  }

  createResponseChart(): void {
    const ctx = document.getElementById('responseChart') as HTMLCanvasElement;
    new Chart(ctx, {
      type: 'doughnut',
      data: {
        labels: ['Rejected', 'On Hold', 'Approved'],
        datasets: [
          {
            data: [942, 25, 2452],
            backgroundColor: ['#e74c3c', '#f39c12', '#27ae60']
          }
        ]
      },
      options: {
        responsive: true
      }
    });
  }
}
