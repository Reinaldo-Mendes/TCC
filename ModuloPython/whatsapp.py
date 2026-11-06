import pywhatkit
import sys
import datetime as dt

telefone = sys.argv[1]
mensagem = sys.argv[2]

now = dt.datetime.now()
envio = now + dt.timedelta(minutes=1)

pywhatkit.sendwhatmsg_instantly(telefone, mensagem, 10, False, 3)