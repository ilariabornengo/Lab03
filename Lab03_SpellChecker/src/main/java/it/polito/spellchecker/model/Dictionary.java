package it.polito.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	List <String> paroleIta= new LinkedList <String>();
	List <String> paroleEng= new LinkedList <String>();
	String linguaUsata=null;
public void loadDictionary(String language) {
	
	if(language.equalsIgnoreCase("Italian")) 
	{
		linguaUsata="Italian";
		try {
			FileReader fr= new FileReader("src/main/resources/Italian.txt");
			BufferedReader br= new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null) 
			{
				paroleIta.add(word);
			}
			br.close();
			}catch(IOException o) {
			System.out.println("Errore nella lettura del file");
			}
	}
	else if( language.equalsIgnoreCase("English")) 
	{
		linguaUsata="English";
		try {
			FileReader fr= new FileReader("src/main/resources/English.txt");
			BufferedReader br= new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null) {
			paroleEng.add(word);
			}
			br.close();
			}catch(IOException o) {
			System.out.println("Errore nella lettura del file");
			}
	}
	}

	public List<RichWord> spellCheckTestTextLinear(List<String> imputTextList)
	{
		List<RichWord> lista=new LinkedList<RichWord>();
		if(linguaUsata.equals("Italian"))
		{
			for(String s:imputTextList)
			{
				if(paroleIta.contains(s))
				{
					RichWord parolaOK=new RichWord(s,true);
					lista.add(parolaOK);
				}
				else
				{
					RichWord parolaNO=new RichWord(s,false);
					lista.add(parolaNO);
				}
			}
		}
		else if(linguaUsata.equals("English"))
		{
			for(String s:imputTextList)
			{
				if(paroleEng.contains(s))
				{
					RichWord parolaOK=new RichWord(s,true);
					lista.add(parolaOK);
				}
				else
				{
					RichWord parolaNO=new RichWord(s,false);
					lista.add(parolaNO);
				}
			}
		}
		return lista;
	}
	public List<RichWord> spellCheckTestDichotomic(List<String> imputTextList)
	{
		int minimo=0;
		int massimo=0;
		List<RichWord> lista=new LinkedList<RichWord>();
		if(linguaUsata.equals("Italian"))
		{
			minimo=0;
			massimo=paroleIta.size();
			
			for(String s:imputTextList)
			{
				boolean trovato=false;
				minimo=0;
				massimo=paroleIta.size();
				while(trovato==false)
				{	
					int diviso=(int)((massimo-minimo)/2);
					int meta=diviso+minimo;
					String parolaMeta=paroleIta.get(meta);
					if(parolaMeta.equals(s))
					{
						RichWord parolaOK=new RichWord(s,true);
						lista.add(parolaOK);
						trovato=true;
					}
					else if(s.compareTo(parolaMeta.toLowerCase())>0)
					{
						minimo=meta;
						massimo=massimo;
					}
					else
					{
						massimo=meta;
						minimo=minimo;
					}
					if(diviso==0 && trovato==false)
					{
						RichWord parolaNO=new RichWord(s,false);
						lista.add(parolaNO);
						trovato=true;
					}
				
				}	
			}
		}else if(linguaUsata.equals("English"))
		{
			minimo=0;
			massimo=paroleEng.size();
			
			for(String s:imputTextList)
			{
				boolean trovato=false;
				minimo=0;
				massimo=paroleEng.size();
				while(trovato==false)
				{	
					int diviso=(int)((massimo-minimo)/2);
					int meta=diviso+minimo;
					String parolaMeta=paroleEng.get(meta);
					if(parolaMeta.equals(s))
					{
						RichWord parolaOK=new RichWord(s,true);
						lista.add(parolaOK);
						trovato=true;
					}
					else if(s.compareTo(parolaMeta.toLowerCase())>0)
					{
						minimo=meta;
						massimo=massimo;
					}
					else
					{
						massimo=meta;
						minimo=minimo;
					}
					if(diviso==0 && trovato==false)
					{
						RichWord parolaNO=new RichWord(s,false);
						lista.add(parolaNO);
						trovato=true;
					}
				
				}	
			}
		}
		return lista;
		}
	
	public List<String> paroleSbagliate(List<RichWord> listaP)
	{
		List<String> paroleS=new LinkedList<String>();
		for(RichWord r:listaP)
		{
			if(r.corretto==false)
			{
				paroleS.add(r.parola);
			}
		}
		return paroleS;
	}
}

