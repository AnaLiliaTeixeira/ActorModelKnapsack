import pandas as pd
from scipy.stats import kruskal
import matplotlib.pyplot as plt

df_results = pd.read_csv('resultsActorModel.csv')
df_knapsack = pd.read_csv('resultsKnapsack.csv')
    
sequentialResults = df_knapsack['Sequential']
actorModelResults = df_results['Actor Model']
phaserResults = df_knapsack[' Phaser:Thread 4']

#Generate boxplot
plt.boxplot([sequentialResults] + [actorModelResults] + [phaserResults], labels=['Sequential', 'Actor Model', 'Phaser:Thread 4'])
plt.xticks(fontsize=8)
plt.show()