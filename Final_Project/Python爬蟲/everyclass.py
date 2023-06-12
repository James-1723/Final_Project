from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
import time
import openpyxl
import os

driver = webdriver.Chrome(ChromeDriverManager().install())
driver.get('https://qrysub.nccu.edu.tw')

#The search button
searchButton = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, '/html/body/app-root/section/div/app-search-form/div/form/div[4]/div/button'))
)
searchButton.click()

#Click the list
numberList = WebDriverWait(driver, 10).until(
    EC.element_to_be_clickable((By.XPATH, '//*[@id="mat-select-value-1"]/span/span'))
)
numberList.click()

#Choose the maximum number per page
number = WebDriverWait(driver, 10).until(
    EC.element_to_be_clickable((By.XPATH, '//*[@id="mat-option-5"]/span')))
number.click()

#NextPage
nextPage = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.XPATH, '/html/body/app-root/section/div/app-search-result/div/mat-paginator[1]/div/div/div[2]/button[3]')))

#Open & direct file
os.chdir('/Users/USER/Documents/數位創作/程式語言/Python/final/bin')
wb = openpyxl.load_workbook('EveryClass.xlsx')
s1 = wb['工作表1']

#Method
def search(time):
    
    WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.CLASS_NAME, 'subject-name')))
    
    subjects = driver.find_elements(By.CSS_SELECTOR, '.cdk-row td:nth-child(3), .cdk-row td:nth-child(4), .cdk-row td:nth-child(5), .cdk-row td:nth-child(7)')

    i = 1
    m = 2
    
    if time > 1:
        
        m = time + 100 * (time - 1)
        
    for e in subjects:
        
        if i > 4:
            
            i -= 4
            
        if i % 4 == 0:
            
            c = ('D{}' .format(m))
            s1[c].value = e.text
            m+=1
            
        elif i % 2 == 0:
        
            c = ('B{}' .format(m))
            s1[c].value = e.text
        
        elif i % 3 == 0:
        
            c = ('C{}' .format(m))
            s1[c].value = e.text
            
        else:
            c = ('A{}' .format(m))
            s1[c].value = e.text
            
        i += 1

    wb.save('Every.xlsx')

#Every Page
page =  1
while (page <= 5):
    
    search(page)
    nextPage.click()
    page+=1
    
driver.quit

