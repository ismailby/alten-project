import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PanelMenuComponent } from 'app/shared/ui/panel-menu/panel-menu.component';
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';

@Component({
  selector: 'app-dashboard-layout-component',
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent],
  templateUrl: './dashboard-layout-component.component.html',
  styleUrl: './dashboard-layout-component.component.css'
})
export class DashboardLayoutComponentComponent {
  title = "ALTEN SHOP";
}
