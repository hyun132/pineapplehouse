import requests
import json
import pandas as pd
from sqlalchemy import create_engine
import pymysql

db_connection_str = 'mysql+pymysql://ssafy:ssafy@localhost:3306/happyhouse'
db_connection = create_engine(db_connection_str)
conn = db_connection.connect()

def get_sido_info():
    down_url = 'https://new.land.naver.com/api/regions/list?cortarNo=0000000000'
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/102378?ms=37.5018495,127.0438028,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    temp=list(pd.DataFrame(temp["regionList"])["cortarNo"])
    return temp
def get_gungu_info(sido_code):
    down_url = 'https://new.land.naver.com/api/regions/list?cortarNo='+sido_code
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/102378?ms=37.5018495,127.0438028,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    temp=list(pd.DataFrame(temp['regionList'])["cortarNo"])
    return temp
def get_dong_info(gungu_code):
    down_url = 'https://new.land.naver.com/api/regions/list?cortarNo='+gungu_code
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/102378?ms=37.5018495,127.0438028,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    temp=list(pd.DataFrame(temp['regionList'])["cortarNo"])
    return temp
def get_apt_list(dong_code):
    down_url = 'https://new.land.naver.com/api/regions/complexes?cortarNo='+dong_code+'&realEstateType=APT&order='
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Host": "new.land.naver.com",
        "Referer": "https://new.land.naver.com/complexes/102378?ms=37.5018495,127.0438028,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    try:
        temp=list(pd.DataFrame(temp['complexList'])["complexNo"])
    except:
        temp=[]
    return temp


def get_apt_info(apt_code):
    down_url = 'https://new.land.naver.com/api/complexes/'+apt_code+'?sameAddressGroup=false'
    r = requests.get(down_url,data={"sameAddressGroup":"false"},headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.482968,127.0634,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp=json.loads(r.text)
    return temp

def get_school_info(apt_code):
    down_url = 'https://new.land.naver.com/api/complexes/'+apt_code+'/schools'
    r = requests.get(down_url,headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.482968,127.0634,16&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp_school=json.loads(r.text)
    return temp_school
##################가격정보
def apt_price(apt_code,index):
    p_num=temp["complexPyeongDetailList"][index]["pyeongNo"]
    down_url = 'https://new.land.naver.com/api/complexes/'+apt_code+'/prices?complexNo='+apt_code+'&tradeType=A1&year=5&priceChartChange=true&areaNo='+p_num+'&areaChange=true&type=table'

    r = requests.get(down_url,headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.4830877,127.0579863,15&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp_price=json.loads(r.text)
    return temp_price

def apt_price_chart(apt_code,index):
    p_num=temp["complexPyeongDetailList"][index]["pyeongNo"]
    down_url = 'https://new.land.naver.com/api/complexes/'+apt_code+'/prices?complexNo='+apt_code+'&tradeType=A1&year=5 & priceChartChange = false &areaNo='+p_num+'&type=chart'
    #https: // new.land.naver.com / api / complexes / 2026 / prices?complexNo = 2026 & tradeType = A1 & year = 5 & priceChartChange = false & type = chart
    r = requests.get(down_url,headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.4830877,127.0579863,15&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp_price=json.loads(r.text)
    return temp_price


def apt_img(apt_code,index):
    p_num=temp["complexPyeongDetailList"][index]["pyeongNo"]            #def apt_price_chart(apt_code,index):
    p_num=temp["complexPyeongDetailList"][index]["pyeongNo"]
    down_url = 'https://new.land.naver.com/api/complexes/overview/'+apt_code+'?complexNo='+apt_code
    #https: // new.land.naver.com / api / complexes / 2026 / prices?complexNo = 2026 & tradeType = A1 & year = 5 & priceChartChange = false & type = chart
    #https://new.land.naver.com/api/complexes/overview/128305?complexNo=128305
    r = requests.get(down_url,headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.4830877,127.0579863,15&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp_price=json.loads(r.text)
    return temp_price

    down_url = 'https://new.land.naver.com/api/complexes/'+apt_code+'/prices?complexNo='+apt_code+'&tradeType=A1&year=5 & priceChartChange = false &areaNo='+p_num+'&type=chart'
    #https: // new.land.naver.com / api / complexes / 2026 / prices?complexNo = 2026 & tradeType = A1 & year = 5 & priceChartChange = false & type = chart
    r = requests.get(down_url,headers={
        "Accept-Encoding": "gzip",
        "Host": "new.land.naver.com",
        "authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njg4NDAwMzksImV4cCI6MTY2ODg1MDgzOX0.vI2xBRbvcuU8z0wr14Bs3iaijdolmy0JsevqDtOcX1k",
        "Referer": "https://new.land.naver.com/complexes/"+apt_code+"?ms=37.4830877,127.0579863,15&a=APT&b=A1&e=RETAIL",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-origin",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    })
    r.encoding = "utf-8-sig"
    temp_price=json.loads(r.text)
    return temp_price

sido_list=get_sido_info()
for m in range(len(sido_list)):
    gungu_list=get_gungu_info(sido_list[m])
    gungu_apt_list=[0]*len(gungu_list)
    # for j in range(1):#구 마다 하나씩 저장
    for j in range(3,len(gungu_list)):#구 마다 하나씩 저장
        dong_list=get_dong_info(gungu_list[j])
        dong_apt_list=[0]*len(dong_list)
        for k in range(len(dong_list)):#동마다 하나씩 저장
            apt_list=get_apt_list(dong_list[k])
            apt_list_data=[0]*len(apt_list)
            for n in range(0,len(apt_list)):#아파트 마다 하나씩 저장
                temp=get_apt_info(apt_list[n])

                try:
                    area_list=temp["complexDetail"]["pyoengNames"].split(", ")
                    ex_flag=1
                except KeyError:
                    ex_flag=0
                if ex_flag==1:

                    temp_data = pd.DataFrame(index=range(len(area_list)))
                    temp_school = get_school_info(apt_list[n])


                    for i in range(len(area_list)):
                        print(temp["complexDetail"]["address"], temp["complexDetail"]["complexName"])
                        temp_data.loc[i, "dongCode"] = dong_list[k]
                        temp_data.loc[i,"aptName"]=temp["complexDetail"]["complexName"]
                        temp_data.loc[i,"pyoeng"]=area_list[i]
                        temp_data.loc[i,"address"]=temp["complexDetail"]["address"]+" "+temp["complexDetail"]["detailAddress"]
                        try:
                            temp_data.loc[i,"roadAddress"]=temp["complexDetail"]["roadAddressPrefix"]+" "+temp["complexDetail"]["roadAddress"]
                        except KeyError:
                            temp_data.loc[i,"roadAddress"]=temp["complexDetail"]["roadAddressPrefix"]
                        temp_data.loc[i,"latitude"]=temp["complexDetail"]["latitude"]
                        temp_data.loc[i,"longitude"]=temp["complexDetail"]["longitude"]
                        temp_data.loc[i,"totalHouseholdCount"]=temp["complexDetail"]["totalHouseholdCount"]
                        temp_data.loc[i,"totalLeaseHouseholdCount"]=temp["complexDetail"]["totalLeaseHouseholdCount"]
                        temp_data.loc[i,"highFloor"]=temp["complexDetail"]["highFloor"]
                        temp_data.loc[i,"lowFloor"]=temp["complexDetail"]["lowFloor"]
                        try:
                            temp_data.loc[i,"representativePhoto"]= 'https://landthumb-phinf.pstatic.net'+apt_img(temp["complexDetail"]["complexNo"],0)["grandPlanUrl"]
                        except KeyError:
                            temp_data.loc[i,"representativePhoto"]=""
                        try:
                            temp_data.loc[i, "aptCode"] = temp["complexDetail"]["complexNo"]
                        except KeyError:
                            temp_data.loc[i, "aptCode"] = ""
                        try:
                            temp_data.loc[i,"batlRatio"]=temp["complexDetail"]["batlRatio"]
                        except KeyError:
                            temp_data.loc[i,"batlRatio"]=""
                        try:
                            temp_data.loc[i,"btlRatio"]=temp["complexDetail"]["btlRatio"]
                        except KeyError:
                            temp_data.loc[i,"btlRatio"]=""
                        try:
                            temp_data.loc[i,"parkingPossibleCount"]=temp["complexDetail"]["parkingPossibleCount"]
                        except KeyError:
                            temp_data.loc[i,"parkingPossibleCount"]=""
                        try:
                            temp_data.loc[i,"constructionCompanyName"]=temp["complexDetail"]["constructionCompanyName"]
                        except KeyError:
                            temp_data.loc[i,"constructionCompanyName"]=""
                        try:
                            temp_data.loc[i,"heatMethodTypeCode"]=temp["complexDetail"]["heatMethodTypeCode"]
                        except KeyError:
                            temp_data.loc[i,"heatMethodTypeCode"]=""
                        try:
                            temp_data.loc[i,"supplyArea"]=temp["complexPyeongDetailList"][i]["supplyArea"]
                        except KeyError:
                            temp_data.loc[i,"supplyArea"]=""
                        try:
                            temp_data.loc[i,"exclusiveArea"]=temp["complexPyeongDetailList"][i]["exclusiveArea"]
                        except KeyError:
                            temp_data.loc[i,"exclusiveArea"]=""
                        try:
                            temp_data.loc[i,"exclusiveRate"]=temp["complexPyeongDetailList"][i]["exclusiveRate"]
                        except KeyError:
                            temp_data.loc[i,"exclusiveRate"]=""
                        try:
                            temp_data.loc[i,"roomCnt"]=temp["complexPyeongDetailList"][i]["roomCnt"]
                        except KeyError:
                            temp_data.loc[i,"roomCnt"]=""
                        try:
                            temp_data.loc[i,"bathroomCnt"]=temp["complexPyeongDetailList"][i]["bathroomCnt"]
                        except KeyError:
                            temp_data.loc[i,"bathroomCnt"]=""
                        try:
                            temp_data.loc[i,"householdCountByPyeong"]=temp["complexPyeongDetailList"][i]["householdCountByPyeong"]
                        except KeyError:
                            temp_data.loc[i,"householdCountByPyeong"]=""
                        try:
                            temp_data.loc[i, "dealPrice"] = \
                                temp["complexPyeongDetailList"][i]["articleStatistics"]["dealPriceString"]
                        except KeyError:
                            temp_data.loc[i, "dealPrice"] = ""
                        try:
                            temp_data.loc[i, "leasePrice"] = \
                                temp["complexPyeongDetailList"][i]["articleStatistics"]["leasePriceString"]
                        except KeyError:
                            temp_data.loc[i, "leasePrice"] = ""
                        try:
                            temp_data.loc[i, "rentPrice"] = \
                                temp["complexPyeongDetailList"][i]["articleStatistics"]["rentPriceString"]
                        except KeyError:
                            temp_data.loc[i, "rentPrice"] = ""


                dong_apt_list[k] = pd.DataFrame(columns=temp_data.columns)
                copied = temp_data[:]
                copied.to_sql(name='houseinfo', con=db_connection, if_exists='append', index=False)
    conn.close()
