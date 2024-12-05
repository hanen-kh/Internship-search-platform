import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrl: './offers.component.css'
})
export class OffersComponent implements OnInit{
  constructor(private router:Router){
  
  }
    ngOnInit(): void {
      throw new Error('Method not implemented.');
    }
    offers = [
      { company: 'ASE', location: 'Paris', description: 'Développeur Python en IA pour 6 mois. Rejoignez notre équipe dynamique et travaillez sur des projets innovants en machine learning.', postedDate: '2024-11-20', region: 'Paris' },
      { company: 'ELYADATA', location: 'Tunis', description: 'Stage machine learning.pour 6 mois. Rejoignez notre équipe dynamique et travaillez sur des projets innovants en machine learning.', postedDate: '2024-11-18', region: 'Tunis' },
      { company: 'TechX', location: 'Lyon', description: 'Développeur Backend Node.js.pour 6 mois. Rejoignez notre équipe dynamique et travaillez sur des projets innovants en machine learning.', postedDate: '2024-11-17', region: 'Lyon' },
      { company: 'SoftCom', location: 'Marseille', description: 'Analyste données pour entreprise.pour 6 mois. Rejoignez notre équipe dynamique et travaillez sur des projets innovants en machine learning.', postedDate: '2024-11-15', region: 'Marseille' },
      { company: 'FutureVision', location: 'Paris', description: 'Spécialiste en Cloud et DevOps.pour 6 mois.Rejoignez notre équipe dynamique et travaillez sur des projets innovants en machine learning.', postedDate: '2024-11-14', region: 'Paris' },
      { company: 'InnovaTech', location: 'Tunis', description: 'Frontend Angular pour e-commerce.pour 6 mois. Rejoignez notre équipe dynamique et travaillez sur des projets innovants en machine learning.', postedDate: '2024-11-13', region: 'Tunis' },
      { company: 'DataWorld', location: 'Lyon', description: 'Data Scientist.pour 6 mois. Rejoignez notre équipe dynamique et travaillez sur des projets innovants en machine learning.', postedDate: '2024-11-12', region: 'Lyon' },
      { company: 'SmartSolutions', location: 'Paris', description: 'Développeur Full Stack.', postedDate: '2024-11-10', region: 'Paris' },
      { company: 'NextGen', location: 'Marseille', description: 'Consultant IT.', postedDate: '2024-11-09', region: 'Marseille' },
      { company: 'BlueTech', location: 'Tunis', description: 'Développeur Mobile Android.', postedDate: '2024-11-08', region: 'Tunis' },
      { company: 'Innovators', location: 'Paris', description: 'Machine Learning Engineer.', postedDate: '2024-11-07', region: 'Paris' },
      { company: 'FutureBytes', location: 'Lyon', description: 'Cybersecurity Specialist.', postedDate: '2024-11-06', region: 'Lyon' },
      { company: 'AlgoSoft', location: 'Tunis', description: 'Développeur IA et algorithmes.', postedDate: '2024-11-05', region: 'Tunis' },
      { company: 'DataHive', location: 'Marseille', description: 'Architecte Cloud.', postedDate: '2024-11-04', region: 'Marseille' },
      { company: 'CloudNext', location: 'Lyon', description: 'DevOps Engineer.', postedDate: '2024-11-03', region: 'Region1' },
    ];
    keyword: string = ''; // Mot-clé pour la recherche
    region: string = ''; // Région pour la recherche
  
    filteredOffers = [...this.offers];
  
    searchOffers(keyword: string, region: string) {
      // Convertir les paramètres en minuscules pour garantir une recherche insensible à la casse
      const lowerKeyword = keyword?.toLowerCase() || '';
      const lowerRegion = region?.toLowerCase() || '';
    
      // Filtrer les offres en fonction du mot-clé et de la région
      this.filteredOffers = this.offers.filter(offer => {
        const matchesKeyword = lowerKeyword
          ? offer.description.toLowerCase().includes(lowerKeyword)
          : true; // Si aucun mot-clé, accepter toutes les descriptions
    
        const matchesRegion = lowerRegion
          ? offer.region.toLowerCase() === lowerRegion
          : true; // Si aucune région, accepter toutes les régions
    
        return matchesKeyword && matchesRegion;
      });
    }
    
  
    highlightKeyword(text: string, keyword: string): string {
      if (!keyword) return text;
      const regex = new RegExp(`(${keyword})`, 'gi');
      return text.replace(regex, `<b>$1</b>`);
    }
  }