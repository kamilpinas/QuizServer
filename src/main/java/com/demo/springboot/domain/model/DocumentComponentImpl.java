package com.demo.springboot.domain.model;

import com.demo.springboot.domain.dto.AnswerDto;
import com.demo.springboot.domain.dto.Questions;
import com.demo.springboot.rest.QuizApiController;
import com.demo.springboot.service.impl.QuizCode;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DocumentComponentImpl implements DocumentComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentComponentImpl.class);
    @Override
    public void createDocument(ArrayList<AnswerDto> answerDto, String fileDestination) {
        try {
            Document document = new Document();
            ArrayList<Questions> quiz = new ArrayList<Questions>(QuizCode.readData());
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileDestination));
            document.open();
            PdfPTable table = new PdfPTable(4);
            table.setWidths(new int[]{1, 1, 1,1});
            table.addCell(createCell("QUESTION ID:", 2, Element.ALIGN_CENTER));
            table.addCell(createCell("CLIENT'S ANSWERS:", 2, Element.ALIGN_CENTER));
            table.addCell(createCell("CORRECT ANSWERS", 2, Element.ALIGN_CENTER));
            table.addCell(createCell("IF CORRECT", 2, Element.ALIGN_CENTER));

            for(int i=0;i<answerDto.size();i++) {
                String id=""+i;
                table.addCell(createCell(id, 1, Element.ALIGN_CENTER));
                table.addCell(createCell(Arrays.toString(answerDto.get(i).getSelectedAnswers()), 1, Element.ALIGN_CENTER));
                table.addCell(createCell(quiz.get(i).getCorrectAnswers(), 1, Element.ALIGN_CENTER));
                if(QuizCode.checkAnswer(answerDto.get(i).getSelectedAnswers(),quiz.get(i).getCorrectAnswers(),Integer.parseInt(quiz.get(i).getPoints()))>0){
                    table.addCell(createCell("GOOD ANSWER", 1, Element.ALIGN_CENTER));
                }
                else{
                    table.addCell(createCell("WRONG ANSWER", 1, Element.ALIGN_CENTER));
                }
            }

            if(QuizCode.ifPassed(QuizApiController.getYourPoints())){
                document.add(new Phrase("Test passed. Score:"+QuizApiController.getYourPoints()+"/"+QuizCode.countMax(quiz)+"\n"));
            }
            else{
                document.add(new Phrase("Test failed. Score:"+QuizApiController.getYourPoints()+"/"+QuizCode.countMax(quiz)+"\n"));
            }
            document.add(new Phrase("Required points to pass: "+QuizCode.getRequiredPoints()+"\n"));
            document.add(new Phrase("Full report below: \n"));
            document.add(table);
            setBackgroundAsGradient(document, writer);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.error("i can't create document or file not exists");
        }
    }

    private PdfPCell createCell(String content, float borderWidth, int alignment) {
        final String FONT = "static/.ttf";
        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setHorizontalAlignment(alignment);
        cell.setPaddingTop(3);
        cell.setPaddingBottom(6);
        cell.setPaddingLeft(3);
        cell.setPaddingRight(3);
        return cell;
    }

    private void setBackgroundAsGradient(Document document, PdfWriter writer) {
        Rectangle pageSize = document.getPageSize();
        PdfShading axial = PdfShading.simpleAxial(writer,
                pageSize.getLeft(pageSize.getWidth()/10), pageSize.getBottom(),
                pageSize.getRight(pageSize.getWidth()/10), pageSize.getBottom(),
                new BaseColor(128, 193, 255),
                new BaseColor(187, 153, 255), true, true);
        PdfContentByte canvas = writer.getDirectContentUnder();
        canvas.paintShading(axial);
    }
}
