package acxmaster;

// I/O and buffered I/O deps
import java.io.*;

// Settings class
class Settings {
	public Settings(Boolean save, Mode mode, Master master, GraphicEQ graphicEQ, Options options, Targets targets, Export export) {
		try {
			if (save) {
				FileWriter writer = new FileWriter("acxmaster.conf");
				writer.write(
					"mode="+String.valueOf(mode.getMode())+"\n"+
					"ffmpeg="+master.getFFmpegPath()+"\n"+
					"1b="+graphicEQ.getOneBand()+"\n"+
					"2b="+graphicEQ.getTwoBand()+"\n"+
					"3b="+graphicEQ.getThreeBand()+"\n"+
					"4b="+graphicEQ.getFourBand()+"\n"+
					"5b="+graphicEQ.getFiveBand()+"\n"+
					"6b="+graphicEQ.getSixBand()+"\n"+
					"7b="+graphicEQ.getSevenBand()+"\n"+
					"8b="+graphicEQ.getEightBand()+"\n"+
					"9b="+graphicEQ.getNineBand()+"\n"+
					"10b="+graphicEQ.getTenBand()+"\n"+
					"11b="+graphicEQ.getElevenBand()+"\n"+
					"12b="+graphicEQ.getTwelveBand()+"\n"+
					"13b="+graphicEQ.getThirteenBand()+"\n"+
					"14b="+graphicEQ.getFourteenBand()+"\n"+
					"15b="+graphicEQ.getFifteenBand()+"\n"+
					"16b="+graphicEQ.getSixteenBand()+"\n"+
					"17b="+graphicEQ.getSeventeenBand()+"\n"+
					"18b="+graphicEQ.getEighteenBand()+"\n"+
					"rnnn="+options.getRnnn()+"\n"+
					"stereo="+String.valueOf(options.getStereo())+"\n"+
					"declick="+options.getDeclick()+"\n"+
					"gate="+options.getGate()+"\n"+
					"noise="+options.getNoise()+"\n"+
					"nowarn="+String.valueOf(options.getNoWarn())+"\n"+
					"custom="+options.getCustom()+"\n"+
					"i="+String.valueOf(targets.getI())+"\n"+
					"lra="+String.valueOf(targets.getLRA())+"\n"+
					"tp="+String.valueOf(targets.getTP())+"\n"+
					"ar="+String.valueOf(export.getSampleRate())+"\n"+
					"ab="+String.valueOf(export.getBitRate())+"\n"+
					"codec="+export.getCodec()+"\n"+
					"sample_fmt="+export.getBitDepth()+"\n"+
					"compression_level="+String.valueOf(export.getCompressionLevel())+"\n"+
					"extension="+export.getExtension()
				);
				writer.close();
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(new File("acxmaster.conf")));
				String configLine;
				while ((configLine = reader.readLine()) != null) {
					String[] tokens = configLine.split("=", 2);
					switch (tokens[0].toLowerCase()) {
						case "mode":
							try {mode.setMode(Boolean.parseBoolean(tokens[1]));} catch (Exception exception) {}
							break;
						case "ffmpeg":
							master.setFFmpegPath(tokens[1]);
							break;
						case "1b":
							graphicEQ.setOneBand(tokens[1]);
							break;
						case "2b":
							graphicEQ.setTwoBand(tokens[1]);
							break;
						case "3b":
							graphicEQ.setThreeBand(tokens[1]);
							break;
						case "4b":
							graphicEQ.setFourBand(tokens[1]);
							break;
						case "5b":
							graphicEQ.setFiveBand(tokens[1]);
							break;
						case "6b":
							graphicEQ.setSixBand(tokens[1]);
							break;
						case "7b":
							graphicEQ.setSevenBand(tokens[1]);
							break;
						case "8b":
							graphicEQ.setEightBand(tokens[1]);
							break;
						case "9b":
							graphicEQ.setNineBand(tokens[1]);
							break;
						case "10b":
							graphicEQ.setTenBand(tokens[1]);
							break;
						case "11b":
							graphicEQ.setElevenBand(tokens[1]);
							break;
						case "12b":
							graphicEQ.setTwelveBand(tokens[1]);
							break;
						case "13b":
							graphicEQ.setThirteenBand(tokens[1]);
							break;
						case "14b":
							graphicEQ.setFourteenBand(tokens[1]);
							break;
						case "15b":
							graphicEQ.setFifteenBand(tokens[1]);
							break;
						case "16b":
							graphicEQ.setSixteenBand(tokens[1]);
							break;
						case "17b":
							graphicEQ.setSeventeenBand(tokens[1]);
							break;
						case "18b":
							graphicEQ.setEighteenBand(tokens[1]);
							break;
						case "rnnn":
							options.setRnnn(tokens[1]);
							break;
						case "stereo":
							try {options.setStereo(Boolean.parseBoolean(tokens[1]));} catch (Exception exception) {}
							break;
						case "declick":
							options.setDeclick(tokens[1]);
							break;
						case "gate":
							options.setGate(tokens[1]);
							break;
						case "noise":
							options.setNoise(tokens[1]);
							break;
						case "nowarn":
							try {options.setNoWarn(Boolean.parseBoolean(tokens[1]));} catch (Exception exception) {}
							break;
						case "custom":
							options.setCustom(tokens[1]);
							break;
						case "i":
							try {targets.setI(Float.parseFloat(tokens[1]));} catch (Exception exception) {}
							break;
						case "lra":
							try {targets.setLRA(Float.parseFloat(tokens[1]));} catch (Exception exception) {}
							break;
						case "tp":
							try {targets.setTP(Float.parseFloat(tokens[1]));} catch (Exception exception) {}
							break;
						case "ar":
							try {export.setSampleRate(Integer.parseInt(tokens[1]));} catch (Exception exception) {}
							break;
						case "ab":
							try {export.setBitRate(Integer.parseInt(tokens[1]));} catch (Exception exception) {}
							break;
						case "codec":
							export.setCodec(tokens[1]);
							break;
						case "sample_fmt":
							export.setBitDepth(tokens[1]);
							break;
						case "compression_level":
							try {export.setCompressionLevel(Integer.parseInt(tokens[1]));} catch (Exception exception) {}
							break;
						case "extension":
							export.setExtension(tokens[1]);
							break;
					}
				}
				reader.close();
			}
		} catch (Exception err) {}
	}
}